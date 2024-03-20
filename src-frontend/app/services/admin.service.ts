import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Admin } from '../model/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {

  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  postAdmin(user: Admin): Observable<Admin> {
    console.log(user);

    return this.http.post<Admin>('http://localhost:8081/api/admin/add-admin', user)
      .pipe(
        catchError(this.handleError)
      );


  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 406) {
      alert('Username already exists. Please choose a different username.');
      return throwError('Conflict: Username already exists');

    }
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError('An error occurred. Please try again later.');
  }


  getHotelowners(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/admin/getall-hotelowners", { headers: this.getHeaders() });
  }

  deleteHotelowner(hotelownerId: number): Observable<string> {
    return this.http.delete<string>("http://localhost:8081/api/admin/delete-hotelowner-account" + `/${hotelownerId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/admin/getall-users", { headers: this.getHeaders() });
  }

  deleteUser(userId: number): Observable<string> {
    return this.http.delete<string>("http://localhost:8081/api/admin/delete-user-account" + `/${userId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }


}
