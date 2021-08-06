import { Component, OnInit, Injectable } from '@angular/core';
import { ISuperhero } from '../shared/superhero';
import { CharacterService } from '../shared/character.service';

@Injectable()
@Component({
  selector: 'app-superhero-list',
  templateUrl: './superhero-list.component.html',
  styleUrls: ['./superhero-list.component.css']
})
export class SuperheroListComponent implements OnInit {

  pageTitle = 'Superhero List';
  imageWidth = 100;
  imageMargin = 2;
  showImage = false;
  _listFilter = '';
  get listFilter(): string {
    return this._listFilter;
  }
  set listFilter(temp: string) {
    this._listFilter = temp;
    this.filteredSuperheroes = this._listFilter ? this.performFilter(this._listFilter) : this.superheroes;
  }

  superheroes: ISuperhero[] = [];
  filteredSuperheroes: ISuperhero[] = [];

  constructor(private _characterService: CharacterService) {
    this.superheroes =
      this._characterService.getCharacters();
    this.filteredSuperheroes = this.superheroes;
  }

  ngOnInit() {
  }

  toggleImage() {
    this.showImage = !this.showImage;
  }

  performFilter(filterBy: string): ISuperhero[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.superheroes.filter((metahuman: ISuperhero) =>
      metahuman.name.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  onRankClicked(temp: string): void {
    this.pageTitle = temp;
  }

}
