import { Injectable } from '@angular/core';
import { Password } from '../model/password';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor(private http: HttpClient) { }

  newPasswordGeneration(body: Password): Observable<string> {

    console.log(body);
    return this.http.put<string>("http://localhost:8081/api/password/update", body, { responseType: 'text' as 'json' });
  }
}
