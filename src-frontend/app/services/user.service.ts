import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }






  postUser(user: User): Observable<User> {
    console.log(user);

    return this.http.post<User>('http://localhost:8081/api/user/register', user)

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


  updateUser(user: User, userId: number): Observable<User> {
    console.log(user);

    return this.http.put<User>("http://localhost:8081/api/user/update" + `/${userId}`, user, { headers: this.getHeaders(), responseType: 'text' as 'json' })

      .pipe(
        catchError(this.handleError)
      );
  }

  getUserById(userId: number): Observable<User> {


    return this.http.get<User>("http://localhost:8081/api/user/get-by-id" + `/${userId}`, { headers: this.getHeaders() })
      .pipe(
        catchError((error: any) => {
          console.error(error);
          return throwError(error);
        })
      );
  }





}
