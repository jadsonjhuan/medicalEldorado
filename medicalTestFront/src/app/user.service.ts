import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userServiceUrl = "http://localhost:8080/users";
  constructor(private http: HttpClient) { }

  signin(user: any) {
    return this.http.post(`${this.userServiceUrl}/signin`, user);
  }

}
