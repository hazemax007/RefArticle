import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../_service/category.service';
import { AuthService } from '../_service/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Category } from '../_model/Category';
import { ImageService } from '../_service/image.service';
import { map } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ResponseService } from '../_service/response.service';
import { Response } from '../_model/Response';
import { error } from 'console';
import { Question } from '../_model/Question';
import { ReferenceService } from '../_service/reference.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrl: './category-details.component.scss'
})
export class CategoryDetailsComponent implements OnInit{

  @ViewChild('exampleModal') modal: ElementRef; // Reference to the modal element
  response : Response
  answers: Response[] = []
  question : Question
  responseForm : FormGroup
  currentQuestionId: any;
  currentQuestionIndex: number; // Add a property to hold the index of the selected question
  username:string
  categoryId:any
  category:Category
  answersMap: Map<number, Response[]> = new Map<number, Response[]>(); // Store answers for each question by question ID
  reference:String = ''

  constructor(private categoryService:CategoryService, private authService:AuthService,
    private route:ActivatedRoute , private imageService:ImageService,
    private responseService:ResponseService, private refService:ReferenceService){
      this.response = {
        content : ''
      }
    }


  ngOnInit(): void {
    this.username = this.authService.getUserName();
    this.getCategory(this.route.snapshot.params['categoryId'])
    this.responseForm = new FormGroup({
      content : new FormControl('',Validators.required)
    })

    this.getAllAnswers()

    //this.loadAnswersForQuestions()
  }

  getCategory(id:any){
    this.categoryService.getCategory(id)
    .pipe(
      map(p => this.imageService.createImages(p)))
      .subscribe(
      (response)=>{
        this.category = response
        console.log(this.category)
      }
    )
  }


  openModal(question: Question , index: number) {
    this.question = question
    this.currentQuestionIndex = index; // Set the index of the selected question
    this.currentQuestionId= question.questionId ;
    const modalElement = this.modal.nativeElement as HTMLElement;
    modalElement.classList.add('show');
    modalElement.style.display = 'block';
  }

  closeModal() {
    const modalElement = this.modal.nativeElement as HTMLElement;
    modalElement.classList.remove('show');
    modalElement.style.display = 'none';
    this.responseForm.reset(); // Reset the form when closing the modal
  }

  answerQuestion() {
    const response: Response = {
      content: this.responseForm.get('content')?.value
    };

    this.responseService
      .addOrUpdateResponseToQuestion(this.username, this.currentQuestionId, response)
      .subscribe(
        (data) => {
          console.log('Response added:', data);
          this.getAllAnswers()
          this.closeModal(); // Close the modal after saving the response
        },
        (error) => {
          console.log('Error adding response:', error);
        }
      );
  }

/*fetchAnswersForQuestions() {
    // Loop through questions and fetch answers for each question
    this.category.questions?.forEach((question, index) => {
      this.getAnswer(question.questionId);
    });
  }*/

  /*getAnswer(questionId:any){
    this.responseService.getResponsesByUserAndQuestion(this.username,questionId).subscribe(
      data => {
        this.response = data
        console.log(data)
      }
    )
  }*/

  getAllAnswers(){
    this.responseService.getAllResponses().subscribe(
      data => {
        this.answers = data
        console.log(this.answers)
      }, error => {
        console.log(error)
      }
    )
  }

  getAnswerr(questionId: any) {
    this.responseService.getResponsesByUserAndQuestion(this.username, questionId).subscribe(
      (data: Response[]) => {
        // Store fetched answers in the answersMap using the questionId as the key
        this.answersMap.set(questionId, data);
        console.log(this.answersMap);
      },
      (error) => {
        console.error('Error fetching answers:', error);
      }
    );
  }

  loadAnswersForQuestions(): void {
    // Boucle à travers toutes les questions et récupère les réponses pour chaque question
    this.category.questions?.forEach(question => {
      this.getAnswerr(question.questionId);
      console.log(this.getAnswerr(question.questionId))
    });
  }

  getReference(){
    this.refService.getReference(this.username).subscribe(
      data => {
        this.reference = data
        console.log(this.reference)
      }
    )
  }

}
