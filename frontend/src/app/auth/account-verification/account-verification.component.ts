import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_service/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-account-verification',
  templateUrl: './account-verification.component.html',
  styleUrl: './account-verification.component.scss'
})
export class AccountVerificationComponent implements OnInit{

  token: any

  constructor(private authService:AuthService,
    private route:ActivatedRoute) { }
    
  ngOnInit(): void {
    this.token = this.route.snapshot.params['token']
    this.authService.verify(this.token).subscribe(
      data => {
        console.log(data)
      },error => {
        console.log(error)
      }
    )
  }

}
