import { Response } from './../_model/Response';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

const API_URL = "http://localhost:8080/api/v1/response/"

@Injectable({
  providedIn: 'root'
})
export class ResponseService {

  constructor(private http:HttpClient) { }

  getAllResponses():Observable<any[]>{
    return this.http.get<Response[]>(API_URL + "getAllResponses")
  }

  getResponsesByUserAndQuestion(username:string,questionId:any):Observable<any>{
    return this.http.get<any>(API_URL + "getResponseByUserAndQuestion/" + username + "/" + questionId)
  }

  addOrUpdateResponseToQuestion(username:string,questionId:any, response:Response):Observable<any>{
    return this.http.post<any>(API_URL + "addOrUpdateResponseToQuestion/" + username +"/" + questionId, response);
  }
}
