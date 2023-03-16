import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgxFileDropEntry } from 'ngx-file-drop';
import { VideoService } from '../video.service';

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css'],
})
export class VideoDetailComponent {
  public files: NgxFileDropEntry[] = [];

  saveVideoDetailsForm: FormGroup;
  title: FormControl = new FormControl('');
  description: FormControl = new FormControl('');
  videoStatus: FormControl = new FormControl('');
  tags = ['Pizza', 'Pasta', 'Parmesan'];
  selectedFile: File | undefined;
  videoId = '';
  videoUrl!: string

  constructor(
    private activatedRoute: ActivatedRoute,
    private videoService: VideoService
  ) {
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.videoService.getVideo(this.videoId).subscribe(data => {
      this.videoUrl = data.videoUrl;
    })
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description: this.description,
      videoStatus: this.videoStatus,
    });
  }

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    (files[0].fileEntry as FileSystemFileEntry).file((file) => {
      this.selectedFile = file;
    });
  }

  public fileOver(event: any) {
    console.log(event);
  }

  public fileLeave(event: any) {
    console.log(event);
  }

  onUpload(videoId: string) {
    if (this.selectedFile != null) {
      this.videoService
        .uploadThumbnail(this.selectedFile, videoId)
        .subscribe((data) => {
          console.log(data);
        });
    }
  }
}
