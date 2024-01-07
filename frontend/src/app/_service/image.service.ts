import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Category } from '../_model/Category';
import { FileHandle } from '../_model/FIleHandle';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private sanitizer:DomSanitizer) { }

  public createImages(category:Category){
    const categoryImages:any[] = category.categoryImages
    const categoryImagesToFileHandle:FileHandle[] = []
    for(var i = 0; i < categoryImages?.length; i++){
      const imageFileData = categoryImages[i]
      const imageBlob = this.dataURItoBlob(imageFileData.picByte,imageFileData.type)
      const imageFile = new File([imageBlob] , imageFileData.name ,{ type : imageFileData.type })
      const finalFileHandle : FileHandle = {
        file:imageFile,
        url:this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      }
      categoryImagesToFileHandle.push(finalFileHandle)
    }
    category.categoryImages = categoryImagesToFileHandle
    return category
  }

  

  public dataURItoBlob(picBytes:any,imageType:any){
    const byteString = window.atob(picBytes)
    const arrayBuffer = new ArrayBuffer(byteString.length)
    const int8Array = new Uint8Array(arrayBuffer)
    for(let i =0; i< byteString.length; i++){
      int8Array[i] = byteString.charCodeAt(i)

    }
    const blob = new Blob([int8Array],{type:imageType})
    return blob
  }
}
