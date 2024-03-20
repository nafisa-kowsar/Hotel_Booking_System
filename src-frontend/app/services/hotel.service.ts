import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  private _hotelName: string = '';

  private apiUrl = 'http://localhost:8081/api/hotel';

  getAllHotels(): Observable<any[]> {
    const url = `${this.apiUrl}/getall`;
    return this.http.get<any[]>(url);
  }

  getHotelsByName(hotelName: string): Observable<any[]> {

    const url = `${this.apiUrl}/get-by-name/${hotelName}`;
    return this.http.get<any[]>(url);
  }

  getHotelsByLocation(location: string): Observable<any[]> {

    const url = `${this.apiUrl}/get/location/${location}`;
    return this.http.get<any[]>(url);
  }




  getHotelName(): string {
    return this._hotelName;
  }


  setHotelName(value: string) {
    this._hotelName = value;
  }




}
