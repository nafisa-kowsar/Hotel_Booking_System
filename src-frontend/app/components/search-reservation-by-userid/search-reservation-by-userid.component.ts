// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { Reservation } from 'src/app/model/reservation';
// import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
// import { ReservationService } from 'src/app/services/reservation.service';

// @Component({
//   selector: 'app-search-reservation-by-userid',
//   templateUrl: './search-reservation-by-userid.component.html',
//   styleUrls: ['./search-reservation-by-userid.component.css']
// })
// export class SearchReservationByUseridComponent {

//   reservationList: Reservation[] = [];
//   userId: number = 0;
//   searchResults: any[] = [];

//   constructor(private reservationService: ReservationService, private router: Router, private jwtAdminService: JwtClientAdminService) { }

//   searchByUserId() {


//     this.reservationService.getReservationByUser(this.userId).subscribe(
//       (data: any) => {

//         this.searchResults = JSON.parse(data);


//       },
//       (error: any) => {
//         console.error('Error fetching reservations:', error);
//       }
//     );
//     console.log('Simulating search by User ID:', this.userId);


//     this.searchResults = this.reservationList.filter(reservation => reservation.reservationId === this.userId);









//   }


//   cancelReservation(reservationId: number): void {
//     this.reservationService.manageReservation(reservationId).subscribe((msg) => {
//       console.log("Deleted " + msg);
//       this.searchByUserId();
//     });
//   }

//   logout(): void {

//     this.jwtAdminService.clearStoredToken();

//     this.router.navigate(['/admin-login']);
//   }

// }


import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/model/reservation';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-search-reservation-by-userid',
  templateUrl: './search-reservation-by-userid.component.html',
  styleUrls: ['./search-reservation-by-userid.component.css']
})
export class SearchReservationByUseridComponent {

  reservationList: Reservation[] = [];
  userId: number = 0;
  searchResults: Reservation[] = [];
  pagedSearchResults: Reservation[] = [];
  itemsPerPage: number = 1;
  totalItems: number = 0;
  currentPage: number = 0;

  constructor(private reservationService: ReservationService, private router: Router, private jwtAdminService: JwtClientAdminService) { }

  searchByUserId() {
    this.reservationService.getReservationByUser(this.userId).subscribe(
      (data: any) => {
        this.searchResults = JSON.parse(data);
        this.totalItems = this.searchResults.length;
        this.updatePage();
      },
      (error: any) => {
        console.error('Error fetching reservations:', error);
      }
    );
  }

  updatePage() {
    const startIndex = this.currentPage * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.pagedSearchResults = this.searchResults.slice(startIndex, endIndex);
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.updatePage();
  }

  cancelReservation(reservationId: number): void {
    this.reservationService.manageReservation(reservationId).subscribe((msg) => {
      console.log("Deleted " + msg);
      this.searchByUserId();
    },
      (error: any) => {
        console.error('Error fetching reservations:', error);
        alert("Failed to delete reservation. Reservation is already cancelled.");
      });
  }


}



