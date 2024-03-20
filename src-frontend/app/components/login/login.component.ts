import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/model/authRequest';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  token: any;
  errorMessage: string = '';

  authRequest: AuthRequest = new AuthRequest();

  constructor(private jwtService: JwtClientService, private router: Router) { }

  readFormData(formData: any) {
    this.authRequest.username = formData.form.value.username;
    this.authRequest.password = formData.form.value.password;
    this.getAccessToken();
  }

  async getAccessToken() {
    try {
      this.token = await this.jwtService.getGeneratedToken(this.authRequest).toPromise();
      this.accessApi();
    } catch (error) {
      console.error('Error getting access token:', error);
      this.handleAuthenticationError(error);
    }
  }

  accessApi(): void {
    const decodedToken = this.jwtService.authorizationTest(this.token);

    if (decodedToken) {
      console.log('Decoded Token Claims:', decodedToken);
      localStorage.setItem('jwtToken', this.token);




      const role = decodedToken.role;
      const userId = decodedToken.customerId;

      console.log(role);
      console.log(userId);

      if (role === 'USER') {
        console.log('Navigating to user-dashboard...');
        this.router.navigate(['/user-navbar/user-dashboard']);
        this.router.events.subscribe(event => {
          console.log(event);
        });
      } else {
        console.log('Permission denied. No navigation.');
      }
    } else {
      console.error('Error decoding JWT token');
    }
  }

  handleAuthenticationError(error: any): void {
    if (error.status === 401) {

      this.errorMessage = 'Incorrect username or password. Please try again.';
    } else {

      this.errorMessage = 'Incorrect username or password. Please try again.';
    }
  }



}
