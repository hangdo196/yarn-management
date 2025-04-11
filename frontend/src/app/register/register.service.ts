import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {

  private apiUrl = 'http://localhost:8080/yarn/welcoming'; 

  constructor(private http: HttpClient) {}

  register(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
