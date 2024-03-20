import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-user-navbar',
  templateUrl: './user-navbar.component.html',
  styleUrls: ['./user-navbar.component.css']
})
export class UserNavbarComponent {


  constructor(private router: Router, private jwtAdminService: JwtClientAdminService, private reservationService: ReservationService) { }

  logout(): void {

    this.jwtAdminService.clearStoredToken();
    this.reservationService.clearSelectedRooms();

    this.router.navigate(['/login']);
  }

}
