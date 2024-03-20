import { Component, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/model/reservation';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-hotelowner-cancelled-reservations',
  templateUrl: './hotelowner-cancelled-reservations.component.html',
  styleUrls: ['./hotelowner-cancelled-reservations.component.css']
})
export class HotelownerCancelledReservationsComponent {


  reservationList: Reservation[] = [];
  searchResults: any[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 3;
  totalItems: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private reservationService: ReservationService,
    private jwtService: JwtClientHotelownerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.cancelledReservations();
  }

  cancelledReservations() {
    const loggedInUser = this.jwtService.getHotelOwnerId();
    console.log(loggedInUser);

    if (loggedInUser !== null) {
      this.reservationService.getCancelledReservationByHotel(loggedInUser).subscribe(
        (data: any) => {
          this.reservationList = JSON.parse(data);
          this.updateSearchResults();
        },
        (error: any) => {
          console.error('Error fetching reservations:', error);
          alert("Unable to find Cancelled reservations: " + error.message);

        }
      );
      console.log('Simulating search by Hotel ID:', loggedInUser);
    } else {
      console.error('No logged-in user found.');
    }
  }

  updateSearchResults() {
    const startIndex = this.currentPage * this.itemsPerPage;
    this.searchResults = this.reservationList.slice(startIndex, startIndex + this.itemsPerPage);
    this.totalItems = this.reservationList.length;
  }

  refundAmount(reservationId: number): void {
    this.reservationService.refundAmount(reservationId).subscribe((refundAmount) => {
      console.log("Refund Amount " + refundAmount);
      alert("Refund Amount " + refundAmount);

      this.cancelledReservations();
    },
      (error) => {
        console.error("Error deleting reservation:", error);

        alert("Refund already processed");
      }
    );
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.updateSearchResults();
  }

}
