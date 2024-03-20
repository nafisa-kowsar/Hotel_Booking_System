import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/model/authRequest';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {

  token: any;
  errorMessage: string = '';

  authRequest: AuthRequest = new AuthRequest();

  constructor(private jwtService: JwtClientAdminService, private router: Router) { }

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

      if (role === 'ADMIN') {
        console.log('Navigating to Admin-dashboard...');
        this.router.navigate(['/admin-navbar/admin-dashboard']);
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
