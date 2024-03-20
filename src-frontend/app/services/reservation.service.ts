import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../model/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  constructor(private http: HttpClient,) {

  }

  private selectedRooms: any[] = [];

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getReservationByUser(userId: number): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/reservation/get-by-user" + `/${userId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  getReservationByHotel(hotelId: number): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/reservation/get-by-hotel" + `/${hotelId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  getCancelledReservationByHotel(hotelId: number): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/reservation/cancelled-reservation" + `/${hotelId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  manageReservation(reservationId: number): Observable<string> {
    return this.http.delete<string>("http://localhost:8081/api/reservation/manage-room-reservation" + `/${reservationId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  getReservationByHotelOwner(hotelownerId: number): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8081/api/reservation/get-by-hotelowner" + `/${hotelownerId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  deleteUserReservation(userId: number, reservationId: number) {
    return this.http.delete<void>("http://localhost:8081/api/reservation/cancel-and-refund" + `/${userId}` + `/${reservationId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  refundAmount(reservationId: number): Observable<number> {
    return this.http.get<number>("http://localhost:8081/api/reservation/refundAmount" + `/${reservationId}`, { headers: this.getHeaders(), responseType: 'text' as 'json' });
  }

  getSelectedRooms(): any[] {
    return this.selectedRooms;
  }

  addSelectedRoom(room: any): void {
    if (!this.selectedRooms.some(selectedRoom => selectedRoom.roomId === room.roomId)) {
      this.selectedRooms.push(room);
    }
  }

  clearSelectedRooms(): void {
    this.selectedRooms = [];
  }




}
