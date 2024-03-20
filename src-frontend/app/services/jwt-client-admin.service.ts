import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtClientAdminService {

  constructor(private http: HttpClient) { }


  baseURL: string = 'http://localhost:8081/';

  getGeneratedToken(requestBody: any) {

    return this.http.post(this.baseURL + "api/admin/login", requestBody, { responseType: 'text' as 'json' });

  }

  authorizationTest(token: any) {
    const decodedToken = this.decodeToken(token);


    if (decodedToken) {
      return {
        role: decodedToken.role,
        customerId: decodedToken.customerId
      };
    } else {
      console.error('Error decoding JWT token');
      return null;
    }

  }



  decodeToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      console.error('Error decoding JWT token:', Error);
      return null;
    }
  }

  storeToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  getStoredToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  clearStoredToken(): void {
    localStorage.removeItem('jwtToken');
  }

  // Inside JwtClientAdminService class
  getUserRole(): string | null {
    const storedToken = this.getStoredToken();

    if (storedToken) {
      const decodedToken = this.decodeToken(storedToken);

      if (decodedToken && decodedToken.role) {
        return decodedToken.role;
      } else {
        console.error('Role not found in the decoded token.');
        return null;
      }
    } else {
      console.error('No token found in localStorage.');
      return null;
    }
  }
}
