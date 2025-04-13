import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private apiUrl = '/api/yarn/welcoming'; 

  constructor(private http: HttpClient) {}

  register(): Observable<any> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
