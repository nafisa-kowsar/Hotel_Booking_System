import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';

@Component({
  selector: 'app-hotelowner-navbar',
  templateUrl: './hotelowner-navbar.component.html',
  styleUrls: ['./hotelowner-navbar.component.css']
})
export class HotelownerNavbarComponent {

  constructor(private router: Router, private jwtService: JwtClientHotelownerService) { }

  logout(): void {

    this.jwtService.clearStoredToken();
    this.router.navigate(['/hotelowner-login']);
  }

}
