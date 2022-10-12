import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FileUploadComponent} from './components/fileupload/file-upload.component';
import {HttpClientModule} from "@angular/common/http";
import {JsonRenderComponent} from './components/jsonrender/json-render.component';

@NgModule({
  declarations: [
    AppComponent,
    FileUploadComponent,
    JsonRenderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
