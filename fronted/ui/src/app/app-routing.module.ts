import { VideoDetailComponent } from './video-detail/video-detail.component';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'upload-video',
    component: UploadVideoComponent
  }, {
    path: 'video-detail/:videoId',
    component: VideoDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
