import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// pages
import { FlexLayoutModule } from '@angular/flex-layout';

// composants communs

import { AppComponent } from '../app.component';
import { AvatarImageComponent } from '../avatar-image/avatar-image.component';


@NgModule({

  declarations: [
  //  AvatarImageComponent,
// ici je declare mes coposant partagés
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [], // footer/navbar/ailleurs
})
export class SharedModule {

}
