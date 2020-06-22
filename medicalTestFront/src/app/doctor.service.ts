import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  doctorServiceUrl = "http://localhost:8080/doctors";

  constructor(private http : HttpClient) {}

  list() {
    return this.http.get<any[]>(`${this.doctorServiceUrl}`);
  }
}
