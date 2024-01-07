import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Category } from '../_model/Category';

const API_URL = "http://localhost:8080/api/v1/category/"

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  constructor(private httpClient: HttpClient) { }

  getAllCategories():Observable<any[]>{
    return this.httpClient.get<any[]>(API_URL + "getAllCategories")
  }

  getCategory(categoryId:any):Observable<Category>{
    return this.httpClient.get<Category>(API_URL+"getCategory/"+ categoryId)
  }

}
