import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SuperheroListComponent } from './superhero-list/superhero-list.component';
import { CharacterService } from './shared/character.service';
import { WelcomeComponent } from './welcome/welcome.component';
import { PokemonService } from './shared/pokemon.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { EditListComponent } from './edit-list/edit-list.component';

@NgModule({
  declarations: [
    AppComponent,
    SuperheroListComponent,
    WelcomeComponent,
    ProfileComponent,
    EditListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'welcome', component: WelcomeComponent},
      {path: 'superheroes', component: SuperheroListComponent},
      {path: 'profile/:heroname', component: ProfileComponent},
      {path: '', redirectTo: 'welcome', pathMatch: 'full'},
      {path: '**', redirectTo: 'welcome', pathMatch: 'full'}
    ])
  ],
  providers: [CharacterService, PokemonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
