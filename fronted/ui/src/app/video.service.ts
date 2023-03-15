import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';
import VideoResponse from './upload-video/VideoResponse';

@Injectable({
  providedIn: 'root',
})
export class VideoService {
  private url: string = 'http://localhost:8080/api/videos';
  constructor(private httpClient: HttpClient) {}

  uploadVideo(file: File): Observable<VideoResponse> {
    // Http post to upload file
    // You could upload it like this:
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.httpClient.post<VideoResponse>(this.url, formData);
  }

  uploadThumbnail(file: File, videoId: string): Observable<string> {
    // Http post to upload file
    // You could upload it like this:
    const formData = new FormData();
    formData.append('file', file, file.name);
    formData.append('videoId', videoId);

    return this.httpClient.post(this.url, formData, { responseType: 'text' });
  }
}
