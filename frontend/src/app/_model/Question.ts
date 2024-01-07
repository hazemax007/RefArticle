import { Category } from "./Category"

export interface Question{
    questionId: any
    content: string
    reponse: string
    categories?:Category[]
    responses?:Response[]
}