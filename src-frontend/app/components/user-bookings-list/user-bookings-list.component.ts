import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/model/reservation';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-user-bookings-list',
  templateUrl: './user-bookings-list.component.html',
  styleUrls: ['./user-bookings-list.component.css']
})
export class UserBookingsListComponent implements OnInit {
  reservationList: Reservation[] = [];
  searchResults: any[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 5;
  totalItems: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private reservationService: ReservationService,
    private jwtService: JwtClientService,
    private router: Router
  ) { }

  ngOnInit() {
    this.searchByUserId();
  }

  searchByUserId() {
    const loggedInUser = this.jwtService.getUserId();

    if (loggedInUser !== null) {
      this.reservationService.getReservationByUser(loggedInUser).subscribe(
        (data: any) => {
          this.reservationList = JSON.parse(data);
          this.updateSearchResults();
        },
        (error: any) => {
          console.error('Error fetching reservations:', error);
        }
      );
    } else {
      console.error('No logged-in user found.');
    }
  }

  updateSearchResults() {

    const startIndex = this.currentPage * this.itemsPerPage;
    this.searchResults = this.reservationList.slice(
      startIndex,
      startIndex + this.itemsPerPage
    );
    this.totalItems = this.reservationList.length;
  }

  cancelAndRefund(reservationId: number): void {
    const loggedInUser = this.jwtService.getUserId();

    if (loggedInUser !== null) {
      this.reservationService.deleteUserReservation(loggedInUser, reservationId).subscribe(
        () => {
          console.log('Cancellation Successful and Refund is in progress');
          alert('Cancellation Successful and Refund is in progress');

          this.searchByUserId();
        },
        (error) => {
          console.error('Error canceling and refunding reservation:', error);
          alert('Complete Payment or Refund is already in progress');

        }
      );
    } else {
      console.error('No logged-in user found.');
    }
  }

  addReview(reservationId: number) {
    this.router.navigate(['/user-navbar/review', reservationId]);
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.updateSearchResults();
  }
}