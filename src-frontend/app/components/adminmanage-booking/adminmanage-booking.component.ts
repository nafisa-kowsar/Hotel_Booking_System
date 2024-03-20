import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';

@Component({
  selector: 'app-adminmanage-booking',
  templateUrl: './adminmanage-booking.component.html',
  styleUrls: ['./adminmanage-booking.component.css']
})
export class AdminmanageBookingComponent {


  constructor(private router: Router, private jwtAdminService: JwtClientAdminService) { }

  searchInput: string = ''; // Single search input
  userId: string = ''; // User ID for search
  hotelId: string = ''; // Hotel ID for search

  // Handle search based on the button clicked
  search(criteria: string): void {
    if (criteria === 'userId') {
      // Implement logic to search by User ID using this.userId
      console.log('Searching by User ID:', this.userId);
    } else if (criteria === 'hotelId') {
      // Implement logic to search by Hotel ID using this.hotelId
      console.log('Searching by Hotel ID:', this.hotelId);
    }
  }



  logout(): void {

    this.jwtAdminService.clearStoredToken();

    this.router.navigate(['/admin-login']);
  }


}
