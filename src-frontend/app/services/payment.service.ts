import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { JwtClientService } from './jwt-client.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {



  reservationId: number = 0;

  private totalFareSubject = new BehaviorSubject<number>(0);


  // Method to set the total fare



  totalFare$: Observable<number> = this.totalFareSubject.asObservable();

  constructor(private jwtService: JwtClientService, private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }


  processPayment(reservationId: number, userId: number, paymentMethod: string): Observable<any> {


    const params = new HttpParams()


      .set('userId', userId)
      .set('paymentMethod', paymentMethod);
    console.log(params);
    console.log(this.getHeaders());



    return this.http.post<any>("http://localhost:8081/api/payment/make-payment/" + reservationId, {}, { params, headers: this.getHeaders() })



  }

  getReservationId(): number {
    return this.reservationId;
  }

  setReservationId(reservationId: number): void {
    this.reservationId = reservationId;

  }

  setTotalFare(totalFare: Observable<number>): void {
    totalFare.subscribe((newTotalFare) => {
      this.totalFareSubject.next(newTotalFare);
    });
  }

  // Method to get the current total fare
  getTotalFare(): number {
    return this.totalFareSubject.value;
  }

}
