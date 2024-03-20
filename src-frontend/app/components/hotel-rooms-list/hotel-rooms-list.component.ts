import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HotelService } from 'src/app/services/hotel.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-hotel-rooms-list',
  templateUrl: './hotel-rooms-list.component.html',
  styleUrls: ['./hotel-rooms-list.component.css']
})
export class HotelRoomsListComponent {



  loading: boolean = false;
  roomsList: any[] = [];

  hotelName: string = '';


  constructor(private roomService: RoomService, private reservationService: ReservationService, private hotelService: HotelService,
    private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => this.getRoomsByHotelId(params['hotelId']));

  }

  ngOnInit() {

    this.hotelName = this.hotelService.getHotelName();
  }



  getRoomsByHotelId(hotelId: number) {
    this.loading = true;
    this.roomService.getRoomsByHotel(hotelId).subscribe(
      (results) => {
        console.log("results" + results);
        this.roomsList = results;
        console.log("roomsList" + results);
        this.loading = false;
      },
      (error) => {
        console.error(error);
        alert('Error loading rooms');
        this.loading = false;
      }
    );
  }

  checkAvailability(roomId: number) {

    this.router.navigate(['/user-navbar/check-room-availability', roomId]);

  }

  addToBook(room: any) {

    this.reservationService.addSelectedRoom(room);
    console.log(room);
    alert('Successfully added the room to your selected room list. Proceed to the Booking section to finalize and confirm your reservation.');
  }


}


