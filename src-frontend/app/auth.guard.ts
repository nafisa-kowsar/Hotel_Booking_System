import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtClientService } from './services/jwt-client.service';
import { JwtClientAdminService } from './services/jwt-client-admin.service';
import { JwtClientHotelownerService } from './services/jwt-client-hotelowner.service';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router, private jwtClientAdminService: JwtClientAdminService, private jwtClientService: JwtClientService, private jwtClientHotelownerService: JwtClientHotelownerService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree {

    const expectedRole = route.data['expectedRole'];
    console.log('Expected Role:', route.data['expectedRole']);
    // Dynamically inject the appropriate JWT service based on the expected role
    let jwtService;

    switch (expectedRole) {
      case 'USER':
        jwtService = this.jwtClientService;
        break;
      case 'ADMIN':
        jwtService = this.jwtClientAdminService;
        break;
      case 'HOTEL_OWNER':
        jwtService = this.jwtClientHotelownerService;
        break;
      default:
        // Handle unexpected roles or redirect to a default route
        this.router.navigate(['/home']);
        return false;
    }

    const storedToken = jwtService.getStoredToken();
    console.log('Stored Token:', storedToken);

    if (storedToken) {
      const decodedToken = jwtService.decodeToken(storedToken);
      console.log('Decoded Token:', decodedToken);

      // Check the role from the decoded token and authorize accordingly
      if (decodedToken && decodedToken.role === expectedRole) {

        return true;
      }
    }

    // Redirect to login or unauthorized page
    this.router.navigate(['/home']);
    return false; // Update the URL as needed
  }





}

