import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL = "http://localhost:8080/api/v1/reference/"

@Injectable({
  providedIn: 'root'
})
export class ReferenceService {

  constructor(private http:HttpClient) { }

  getReference(username:string):Observable<string> {
    const headers = new HttpHeaders().set('Accept', 'text/plain');
    return this.http.get<string>(API_URL + 'getReference/' + username , { headers, responseType: 'text' as 'json'})
  }

}
