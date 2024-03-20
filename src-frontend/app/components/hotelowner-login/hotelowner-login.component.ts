import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/model/authRequest';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';

@Component({
  selector: 'app-hotelowner-login',
  templateUrl: './hotelowner-login.component.html',
  styleUrls: ['./hotelowner-login.component.css']
})
export class HotelownerLoginComponent {

  response: any;
  token: any;

  errorMessage: string = '';


  authRequest: AuthRequest = new AuthRequest();



  constructor(private jwtService: JwtClientHotelownerService, private router: Router) { }






  readFormData(formData: any) {

    this.authRequest.username = formData.form.value.username;
    this.authRequest.password = formData.form.value.password;

    this.getAccessToken(this.authRequest);

  }

  public getAccessToken(authRequest: any) {

    let response = this.jwtService.getGeneratedToken(authRequest);

    response.subscribe((genToken) => {
      this.token = genToken; console.log(genToken);
      this.accessApi(this.token)
    },
      (error) => {
        console.error('Error getting access token:', error);
        this.handleAuthenticationError(error);
      }

    );

  }

  public handleAuthenticationError(error: any): void {
    if (error.status === 401) {

      this.errorMessage = 'Incorrect username or password. Please try again.';
    } else {

      this.errorMessage = 'Incorrect username or password. Please try again.';
    }
  }







  public accessApi(token: any): void {
    const decodedToken = this.jwtService.authorizationTest(token);

    if (decodedToken) {
      console.log('Decoded Token Claims:', decodedToken);
      localStorage.setItem('jwtToken', this.token);

      const role = decodedToken.role;
      const hotelOwnerId = decodedToken.customerId;

      console.log(role);
      console.log(hotelOwnerId);
      if (role === 'HOTEL_OWNER') {
        console.log('Navigating to user-dashboard...');
        this.router.navigate(['/hotelowner-navbar/hotelowner-dashboard']);
      }

      else {
        console.log('Permission denied. No navigation.');
      }


    } else {
      console.error('Error accessing API');
    }
  }



}

