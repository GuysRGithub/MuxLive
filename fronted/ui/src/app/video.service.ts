import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private url: string = "http://localhost:8080/api/videos"
  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File) : Observable<any> {
    // Http post to upload file
    // You could upload it like this:
    const formData = new FormData()
    formData.append('file', file, file.name)

    // Headers
    const headers = new HttpHeaders({
      'security-token': 'mytoken'
    })
    return this.httpClient.post(this.url, formData)
  }
}
