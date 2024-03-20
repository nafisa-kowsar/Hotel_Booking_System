import { Injectable } from '@angular/core';
import { HotelOwner } from '../model/hotelowner';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HotelownerService {

  response: any;
  constructor(private http: HttpClient) { }



  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }




  postHotelOwner(hotelOwner: HotelOwner): Observable<any> {

    return this.http.post<HotelOwner>('http://localhost:8081/api/hotelowner/register', hotelOwner)
      .pipe(
        catchError(this.handleError)
      );

  }

  updateHotelOwner(hotelowner: HotelOwner, hotelownerId: number): Observable<HotelOwner> {
    console.log(hotelowner);

    return this.http.put<HotelOwner>("http://localhost:8081/api/hotelowner/update" + `/${hotelownerId}`, hotelowner, { headers: this.getHeaders(), responseType: 'text' as 'json' })

      .pipe(
        catchError(this.handleError)
      );
  }


  getHotelownerById(hotelownerId: number): Observable<HotelOwner> {


    return this.http.get<HotelOwner>("http://localhost:8081/api/hotelowner/get-by-id" + `/${hotelownerId}`, { headers: this.getHeaders() })
      .pipe(
        catchError((error: any) => {
          console.error(error);
          return throwError(error);
        })
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
}
