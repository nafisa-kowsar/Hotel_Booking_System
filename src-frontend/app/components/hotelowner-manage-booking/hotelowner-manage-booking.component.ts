

import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/model/reservation';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-hotelowner-manage-booking',
  templateUrl: './hotelowner-manage-booking.component.html',
  styleUrls: ['./hotelowner-manage-booking.component.css']
})
export class HotelownerManageBookingComponent implements OnInit {

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
    this.searchByHotelOwnerId();
  }

  searchByHotelOwnerId() {
    const loggedInUser = this.jwtService.getHotelOwnerId();
    console.log(loggedInUser);

    if (loggedInUser !== null) {
      this.reservationService.getReservationByHotelOwner(loggedInUser).subscribe(
        (data: any) => {
          this.reservationList = JSON.parse(data);
          this.updateSearchResults();
        },
        (error: any) => {
          console.error('Error fetching reservations:', error);
          alert("Unable to find reservations: " + error.message);

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

  cancelReservation(reservationId: number): void {
    this.reservationService.manageReservation(reservationId).subscribe((msg) => {
      console.log("Deleted " + msg);

      this.searchByHotelOwnerId();
    },
      (error) => {
        console.error("Error deleting reservation:", error);

        alert("Failed to delete reservation. Reservation is already cancelled.");
      }
    );
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.updateSearchResults();
  }
}
