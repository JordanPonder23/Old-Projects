import { Component } from '@angular/core';
import { ISuperhero } from '../hero/hero';


@Component({
    // the selector defines a component directive
    selector: 'app-hero-list',
    /* template: '<html><body><h3>Trevin</h3></body></html>' */
    templateUrl: './hero-list.component.html',
    styleUrls: ['./hero-list.component.css']
})
export class HeroListComponent {
    pageTitle = 'SuperheroList';

    // we need to created two arrays to handle filtering
    // each array holds ISuperhero objects
    superheroes: ISuperhero[] = [];
    filteredSuperheroes: ISuperhero[] = [];

    imageWidth = 100;
    imageMargin = 2;
    showImage = false;

// tslint:disable-next-line: variable-name
    _listFilter = '';

    get listFilter() {
        return this._listFilter;
    }

    set listFilter(temp: string) {
        this._listFilter = temp;

        // our setter has one additional line that sets the filteredList
        // equal to the results of a ternary statement that COULD consult
        // the performFilter() method
        this.filteredSuperheroes = this._listFilter ?
            this.performFilter(this._listFilter) : this.superheroes;
    }

    constructor() {
        // OUR constructor will initialize our two arrays
        // superheroes and filteredSuperheroes
        this.superheroes = [
           {
            name: 'Frozone',
            rank: 4.5,
            ability: 'cold generation',
            organization: 'incredibles',
            image: 'http://www.minecraftjunkie.com/wp-content/uploads/2011/06/Skin1Pc-200x100.jpg'
           },
           {
            name: 'Eraserhead',
            rank: 5,
            ability: 'power nullification',
            organization: 'pro hero',
            image: 'http://www.minecraftjunkie.com/wp-content/uploads/2011/12/minecraft_wallpaper-200x100.jpg'
           },
           {
            name: 'Static Shock',
            rank: 5,
            ability: 'electric manipulation',
            organization: 'bang baby',
            image: 'http://www.minecraftjunkie.com/wp-content/uploads/2011/06/Skin1Pc-200x100.jpg'
           },
           {
            name: 'Jack Jack',
            rank: 2,
            ability: 'what doesnt he have?',
            organization: 'incredibles',
            image: 'http://www.minecraftjunkie.com/wp-content/uploads/2011/12/minecraft_wallpaper-200x100.jpg'
           }
        ];
        // this.superheroes = [];
        // we set the filtered list to the same value as the unfiltered
        this.filteredSuperheroes = this.superheroes;
    }

    performFilter(filterBy: string): ISuperhero[] {
        filterBy = filterBy.toLocaleLowerCase();

        // the filtering is performed here
        return this.superheroes.filter((superhero: ISuperhero) =>
                superhero.name.toLocaleLowerCase().indexOf(filterBy)
                !== -1);
    }

    toggleImage() {
        // is used with the show image button to
        // trigger the *ngIf
        this.showImage = !this.showImage;
    }

    onRankClick(temp: string): void {
        this.pageTitle = temp;
    }
}
