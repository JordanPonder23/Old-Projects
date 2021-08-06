import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../shared/pokemon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private pokes: PokemonService,
              private routerMod: Router) { }

  ngOnInit() {
  }

  buttonClicked() {
    console.log('page changer button clicked');
    this.routerMod.navigate(['/superheroes']);
  }

  pokemonButtonClicked() {
    console.log('pokemon button has been clicked');
    this.pokes.retrievePokemon().subscribe(
      data => {
        const ourField = 'base_experience';
        console.log(data);
        console.log('experience? ', data[ourField]);
      }
    );
  }

  pokemonButtonClickedAgain() {
    console.log('pokemon button clicked again');

    this.pokes.retrievePokemonTwo().subscribe(
      data => {
        console.log(data);
        console.log(data.base_experience);
        console.log(data.id);
        console.log(data.name);
      }
    );
  }
}
