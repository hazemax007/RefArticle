import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ResponseService } from '../_service/response.service';

@Component({
  selector: 'app-answer-form',
  templateUrl: './answer-form.component.html',
  styleUrl: './answer-form.component.scss'
})
export class AnswerFormComponent implements OnInit{
  @ViewChild('exampleModal') modal: ElementRef; // Reference to the modal element
    response : Response
    categoryId : any

    constructor(private resService:ResponseService){}

  ngOnInit(): void {
    
  }

  openModal(categoryId: number) {
    this.categoryId = categoryId; // Retrieve categoryId
    const modalElement = this.modal.nativeElement as HTMLElement;
    modalElement.classList.add('show'); // Show the modal by adding 'show' class
    modalElement.style.display = 'block'; // Display the modal block
  }

  closeModal() {
    const modalElement = this.modal.nativeElement as HTMLElement;
    modalElement.classList.remove('show'); // Hide the modal by removing 'show' class
    modalElement.style.display = 'none'; // Hide the modal block
  }

  addOrUpdateResponseToQuestion(){

  }


}
