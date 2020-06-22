import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class CompromiseService {

  compromiseServiceUrl = "http://localhost:8080/compromises";

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<any[]>(`${this.compromiseServiceUrl}`);
  }
}
