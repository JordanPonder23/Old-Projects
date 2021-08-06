import { Component, Input, OnChanges, Output, EventEmitter, OnInit } from '@angular/core';


@Component({
    selector: 'app-star',
    templateUrl: './star.component.html',
    styleUrls: ['./star.component.css']
})
export class StarComponent implements OnChanges, OnInit {
    starWidth: number;
    @Input() rank: number;
    @Output() rankClick: EventEmitter<string> =
        new EventEmitter<string>();

    constructor() {
        // this.starWidth = 4 * 70 / 5;
    }

    ngOnInit(): void {
        // stuff
    }

    ngOnChanges(): void {
        this.starWidth = this.rank * 70 / 5;
    }

    onClick(): void {
        console.log('clicked');
        this.rankClick.emit(`This rank is ${this.rank}`);
    }
}
