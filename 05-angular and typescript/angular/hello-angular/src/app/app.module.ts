import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeroListComponent } from './hero-list/hero-list.component';
import { PrependPipe } from './shared/prepend.pipe';
import { StarComponent } from './shared/star/star.component';


@NgModule({
  declarations: [
    AppComponent, HeroListComponent, StarComponent, PrependPipe
  ],
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
