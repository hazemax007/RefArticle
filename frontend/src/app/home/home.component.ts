import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../_service/category.service';
import { Category } from '../_model/Category';
import { ImageService } from '../_service/image.service';
import { map } from 'rxjs';
import { AuthService } from '../_service/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit{

  categories : Category[] = [];
  isLoggedIn: boolean;
  username: string;

  constructor(private router: Router , private categoryService:CategoryService , private imageService:ImageService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
    this.getAllCategories()
  }

getAllCategories(){
  this.categoryService.getAllCategories()
    .pipe(
      map((x:Category[],i) => x.map((categoryy:Category) => this.imageService.createImages(categoryy)))
    ).subscribe(
      data => {
        this.categories=data
      }
    )
}

goToCategory(categoryId:any){
  this.router.navigate(['/categoryDetails',categoryId])
}
  
}
