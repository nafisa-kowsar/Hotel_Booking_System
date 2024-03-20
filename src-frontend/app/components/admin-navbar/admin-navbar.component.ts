import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent {

  constructor(private router: Router, private jwtAdminService: JwtClientAdminService) { }

  logout(): void {

    this.jwtAdminService.clearStoredToken();

    this.router.navigate(['/admin-login']);
  }

}
