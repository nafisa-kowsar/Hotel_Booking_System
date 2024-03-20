import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from "jwt-decode";




@Injectable({
  providedIn: 'root'
})
export class JwtClientService {



  constructor(private http: HttpClient) { }


  baseURL: string = 'http://localhost:8081/';

  private loggedInUserId: number | null = null;

  getGeneratedToken(requestBody: any) {

    return this.http.post(this.baseURL + "api/user/login", requestBody, { responseType: 'text' as 'json' });

  }

  authorizationTest(token: any) {
    const decodedToken = this.decodeToken(token);




    let tokenString = "Bearer " + token;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization", tokenString);


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

  getUserId(): number | null {
    const storedToken = this.getStoredToken();

    if (storedToken) {
      const decodedToken = this.authorizationTest(storedToken);

      if (decodedToken) {
        return decodedToken.customerId;
      } else {
        console.error('Error decoding JWT token for hotelOwnerId');
        return null;
      }
    } else {
      console.error('No token found in localStorage for hotelOwnerId');
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

  getStoredToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  clearStoredToken(): void {
    localStorage.removeItem('jwtToken');
  }

  storeToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  setLoggedInUser(userId: number) {
    this.loggedInUserId = userId;
  }


  getLoggedInUser(): number | null {
    return this.loggedInUserId;
  }



}




