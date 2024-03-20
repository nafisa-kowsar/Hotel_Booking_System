import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-check-room-availibility',
  templateUrl: './check-room-availibility.component.html',
  styleUrls: ['./check-room-availibility.component.css']
})
export class CheckRoomAvailibilityComponent {

  loading: boolean = false;
  available: boolean = false;
  checkInDate: string = '';
  checkOutDate: string = '';
  numberOfAdults: number = 0;
  numberOfChildren: number = 0;
  fare: number = 0;
  messageAv: string = '';
  messageFare: string = '';
  todayDate: string = new Date().toISOString().split('T')[0];

  constructor(private roomService: RoomService,
    private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => this.checkAvailability(params['roomId']));

  }

  checkAvailability(roomId: number) {
    this.loading = true;


    if (this.checkInDate && this.checkOutDate) {
      this.roomService.isRoomAvailable(roomId, this.checkInDate, this.checkOutDate).subscribe(
        (result) => {
          console.log("results " + result);
          this.available = result;
          console.log("Is Available " + this.available);
          this.loading = false;
          this.messageAv = 'Room is not available.';
        },
        (error) => {
          console.error(error);
          alert("Check-in date must be before or equal to check-out date");
          this.loading = false;
        }
      );
    } else {
      this.loading = false;
    }
  }



  onSubmitAvailability() {

    this.route.params.subscribe(params => {
      const roomId = params['roomId'];
      this.checkAvailability(roomId);
    });
  }

  calculateFare(roomId: number) {
    this.loading = true;
    if (this.numberOfAdults && this.numberOfChildren) {
      this.roomService.roomFare(roomId, this.numberOfAdults, this.numberOfChildren).subscribe(
        (result) => {
          console.log("results " + result);
          this.fare = result;
          console.log("fare" + this.fare);
          this.loading = false;
          this.messageFare = 'Fare calculation failed.';
        },
        (error) => {
          console.error(error);
          alert("Number of people exceeds the room's maximum capacity.");
          this.loading = false;

        }
      );
    } else {
      this.loading = false;
    }
  }

  onSubmitFare() {

    this.route.params.subscribe(params => {
      const roomId = params['roomId'];
      this.calculateFare(roomId);
    });
  }





}