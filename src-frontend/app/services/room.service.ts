import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, pipe, throwError } from 'rxjs';
import { SearchDTO } from '../model/searchDTO';
import { JwtClientHotelownerService } from './jwt-client-hotelowner.service';


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseUrl = 'http://localhost:8081/api/room';
  reservationId: number = 0;


  constructor(private http: HttpClient, private jwtService: JwtClientHotelownerService) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }



  getRooms(): Observable<any[]> {
    const loggedInUser = this.jwtService.getHotelOwnerId();
    console.log(loggedInUser);
    if (loggedInUser) {
      const url = `${this.baseUrl}/getall-by-hotelowner/${loggedInUser}`;
      return this.http.get<any[]>(url, { headers: this.getHeaders() });
    } else {
      console.error('Error: No logged-in user or hotelId available.');
      return new Observable();
    }
  }

  getRoomsByHotel(hotelId: number): Observable<any[]> {

    const url = `${this.baseUrl}/getall-by-hotelId/${hotelId}`;
    return this.http.get<any[]>(url, { headers: this.getHeaders() });


  }


  searchRooms(location: string, checkInDate: string, checkOutDate: string): Observable<any[]> {
    const url = `${this.baseUrl}/search`;


    const params = new HttpParams()
      .set('location', location)
      .set('checkInDate', checkInDate)
      .set('checkOutDate', checkOutDate);





    return this.http.get<any[]>(url, { params, headers: this.getHeaders() });
  }

  isRoomAvailable(roomId: number, checkInDate: string, checkOutDate: string): Observable<boolean> {

    const url = `${this.baseUrl}/availability/${roomId}`;


    const params = new HttpParams()

      .set('checkInDate', checkInDate)
      .set('checkOutDate', checkOutDate);

    return this.http.get<boolean>(url, { params, headers: this.getHeaders() });

  }

  roomFare(roomId: number, numberOfAdults: number, numberOfChildren: number): Observable<number> {

    const url = `${this.baseUrl}/calculate-fare-room/${roomId}`;


    const params = new HttpParams()

      .set('numberOfAdults', numberOfAdults)
      .set('numberOfChildren', numberOfChildren);

    return this.http.get<number>(url, { params, headers: this.getHeaders() })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 400) {
            return throwError("Number of people exceeds the room's maximum capacity.");
          } else {
            return throwError('An unexpected error occurred. Please try again.');
          }
        })
      );

  }





  addRoom(body: any): Observable<any> {
    const loggedInUser = this.jwtService.getHotelOwnerId();
    console.log(body);
    console.log(loggedInUser);
    if (loggedInUser) {
      const url = `${this.baseUrl}/add/${loggedInUser}`;
      return this.http.post<any>(url, body, { headers: this.getHeaders(), responseType: 'text' as 'json' });
    } else {
      console.error('Error: No logged-in user or hotelId available.');
      return new Observable();
    }


  }

  updateRoom(roomId: number, updatedRoomData: any): Observable<any> {
    const url = `${this.baseUrl}/edit/${roomId}`;
    return this.http.put(url, updatedRoomData, { headers: this.getHeaders(), responseType: 'text' as 'json' })
      .pipe(
        catchError(this.handleError)
      );
  }




  removeRoom(roomId: number): Observable<String> {
    const url = `${this.baseUrl}/remove/${roomId}`;

    return this.http.delete<String>(url, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  private handleError(error: any): Observable<never> {
    console.error('An error occurred', error);

    return new Observable<never>();
  }






}
