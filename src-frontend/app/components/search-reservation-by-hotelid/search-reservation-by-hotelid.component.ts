

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { Reservation } from 'src/app/model/reservation';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-search-reservation-by-hotelid',
  templateUrl: './search-reservation-by-hotelid.component.html',
  styleUrls: ['./search-reservation-by-hotelid.component.css']
})
export class SearchReservationByHotelidComponent {
  reservationList: Reservation[] = [];
  hotelId: number = 0;
  searchResults: any[] = [];
  itemsPerPage: number = 1;
  totalItems: number = 0;
  currentPage: number = 0;

  constructor(private reservationService: ReservationService, private router: Router, private jwtAdminService: JwtClientAdminService) { }

  searchByHotelId() {
    this.reservationService.getReservationByHotel(this.hotelId).subscribe(
      (data: any) => {
        this.reservationList = JSON.parse(data);
        this.updateSearchResults();
      },
      (error: any) => {
        console.error('Error fetching reservations:', error);
      }
    );
    console.log('Simulating search by Hotel ID:', this.hotelId);
  }

  cancelReservation(reservationId: number): void {
    this.reservationService.manageReservation(reservationId).subscribe((msg) => {
      console.log("Deleted " + msg);
      this.searchByHotelId();
    },
      (error: any) => {
        console.error('Error fetching reservations:', error);
        alert("Failed to delete reservation. Reservation is already cancelled.");
      });
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.updateSearchResults();
  }

  updateSearchResults() {
    const startIndex = this.currentPage * this.itemsPerPage;
    this.searchResults = this.reservationList.slice(startIndex, startIndex + this.itemsPerPage);
    this.totalItems = this.reservationList.length;
  }


}
