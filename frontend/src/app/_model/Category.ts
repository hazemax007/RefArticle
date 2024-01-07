import { FileHandle } from "./FIleHandle";
import { Question } from "./Question";


export interface Category{
    categoryId: any
    name: string;
    description: string;
    categoryImages:FileHandle[]
    questions?:Question[]
}