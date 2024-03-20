import { Component } from '@angular/core';

import { JwtClientService } from 'src/app/services/jwt-client.service';

import { ReservationService } from 'src/app/services/reservation.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, forkJoin, map, throwError } from 'rxjs';
import { RoomService } from 'src/app/services/room.service';
import { MatDialog } from '@angular/material/dialog';
import { PaymentDialogComponent } from 'src/app/dialog/payment-dialog/payment-dialog.component';
import { PaymentService } from 'src/app/services/payment.service';
import { Router } from '@angular/router';






@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {


  bookedRoomDetails: any[] = [];
  reservationCheckInDate: string = '';
  reservationCheckOutDate: string = '';
  fare: number = 0;
  inputsDisabled = false;
  todayDate: string = new Date().toISOString().split('T')[0];
  minCheckOutDate: string = this.todayDate;



  constructor(
    private reservationService: ReservationService, private roomService: RoomService, private router: Router,
    private jwtService: JwtClientService, private httpClient: HttpClient, private dialog: MatDialog, private paymentService: PaymentService) { }


  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  ngOnInit() {

    this.calculateAndSetFare();



  }


  calculateAndSetFare(): void {
    const bookedRooms = this.bookedRoomDetails;
    const checkInDate = this.reservationCheckInDate;
    const checkOutDate = this.reservationCheckOutDate;

    if (bookedRooms.length === 0) {

      this.fare = 0;
      return;
    }

    this.calculateFare(bookedRooms, checkInDate, checkOutDate)
      .subscribe(
        (totalFare: number) => {
          if (!isNaN(totalFare)) {
            this.fare = totalFare;
          } else {

            alert("First Select CheckIn and CheckOutDates and then try adding rooms to booking List");
            this.clearForm();
            this.fare = 0;
            this.clearBookedRooms();

          }

        },
        (error) => {
          console.error('Error calculating fare:', error);

        }
      );
  }

  calculateFare(bookedRooms: any[], checkInDate: string, checkOutDate: string): Observable<number> {
    const numberOfDays = this.calculateNumberOfDays(checkInDate, checkOutDate);
    console.log(numberOfDays);

    let fare = 0;


    const roomFareObservables: Observable<number>[] = [];

    for (const bookedRoom of bookedRooms) {

      roomFareObservables.push(this.roomService.roomFare(bookedRoom.roomId, bookedRoom.numberOfAdults, bookedRoom.numberOfChildren).pipe(
        catchError((error) => {
          console.error('Error in roomFare service:', error);
          fare = 0;
          alert("Number of people exceeds the room's maximum capacity.");
          this.removeBookedRoom(bookedRoom.roomId);
          return throwError('Error calculating room fare. Please try again.');
        })
      )
      );
    }


    return forkJoin(roomFareObservables).pipe(
      map((roomFares: number[]) => {

        fare = roomFares.reduce((total, current) => total + current, 0);

        const totalFare = numberOfDays * fare;
        return totalFare;
      })
    );
  }



  private calculateNumberOfDays(checkInDate: string, checkOutDate: string): number {
    const startDate = new Date(checkInDate);
    const endDate = new Date(checkOutDate);
    const timeDifference = endDate.getTime() - startDate.getTime();
    return Math.ceil(timeDifference / (1000 * 3600 * 24)) + 1;

  }



  makeReservation(): void {
    const loggedInUser = this.jwtService.getUserId();
    console.log(loggedInUser);

    if (loggedInUser !== null) {
      const bookedRoomDTO = this.bookedRoomDetails;
      console.log(bookedRoomDTO);


      const params = new HttpParams()
        .set('checkInDate', this.reservationCheckInDate)
        .set('checkOutDate', this.reservationCheckOutDate);

      const url = `http://localhost:8081/api/reservation/make-reservation/${loggedInUser}`;

      this.httpClient.post<any>(url, bookedRoomDTO, { params, headers: this.getHeaders() })
        .subscribe(
          (response) => {
            console.log('Reservation Response:', response);
            const reservationId = response;
            console.log('Reservation Id:', reservationId);
            const actualFare = this.calculateFare(bookedRoomDTO, this.reservationCheckInDate, this.reservationCheckOutDate)
            this.paymentService.setReservationId(reservationId);
            this.paymentService.setTotalFare(actualFare);
            const fare = this.paymentService.getTotalFare();

            const dialogRef = this.dialog.open(PaymentDialogComponent, {
              data: { reservationId: reservationId }
            });

            dialogRef.afterClosed().subscribe(result => {
              if (result) {
                this.router.navigate(['/user-navbar/payment']);
              } else {
                console.log('User closed the dialogue without making a payment.');
              }
            });
          },
          (error) => {
            console.error('Error making reservation:', error);
            alert('Error making reservation: Check Check-In and Check-out Dates or Unable to make reservation from differnet Hotels');
          }
        );
    } else {
      alert
    }

  }

  getSelectedRooms(): any[] {
    console.log('Getting' + this.reservationService.getSelectedRooms());
    return this.reservationService.getSelectedRooms();
  }



  addBookedRoom(room: any, numberOfAdults: number, numberOfChildren: number): void {

    const isRoomAlreadyBooked = this.bookedRoomDetails.some(bookedRoom => bookedRoom.roomId === room.roomId);
    if (!isRoomAlreadyBooked) {
      const bookedRoom: any = { roomId: room.roomId, numberOfAdults, numberOfChildren };

      this.bookedRoomDetails.push(bookedRoom);
      this.calculateAndSetFare();
      this.inputsDisabled = true;
      console.log("fare " + this.fare);
      console.log("added room: " + bookedRoom);
      console.log("bookedRoom list:" + this.bookedRoomDetails);

    } else {
      console.log("Room is already added: " + room.roomId);
      alert("Room is already added");
    }
  }


  removeBookedRoom(roomId: number): void {
    this.bookedRoomDetails = this.bookedRoomDetails.filter(room => room.roomId !== roomId);
    this.calculateAndSetFare();
  }


  clearSelectedRooms(): void {
    this.reservationService.clearSelectedRooms();
    this.bookedRoomDetails = [];
  }

  clearBookedRooms(): void {
    this.bookedRoomDetails = [];
  }

  isAddRoomButtonEnabled(numberOfAdults: number, numberOfChildren: number): boolean {

    return numberOfAdults > 0;
  }

  clearForm(): void {

    this.reservationCheckInDate = '';
    this.reservationCheckOutDate = '';
    this.bookedRoomDetails = [];
    this.inputsDisabled = false;
    this.fare = 0;
  }





}