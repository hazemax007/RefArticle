import { Question } from "./Question"
import { User } from "./User"

export class Response{
    responseId?:any
    content?:string
    question?:Question
    user?:User
}