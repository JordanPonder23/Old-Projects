import { ISuperhero } from './superhero';
import { Injectable } from '@angular/core';

@Injectable()
export class CharacterService {
    getCharacters(): ISuperhero[] {
        return [
            {
              name: 'Frozone',
              ability: 'cold generation',
              organization: 'incredibles',
              image: 'http://www.cultjer.com/img/ug_photo/2014_03/sf2_lg20140331142439.jpg'
            },
            {
              name: 'Eraser',
              ability: 'power nullification',
              organization: 'pro hero',
              image: 'https://media.tenor.co/images/788cc935108fb487b6af1e152bcec6bf/raw'
            },
            {
              name: 'Static Shock',
              ability: 'electric manipulation',
              organization: 'duo',
              image: 'https://t00.deviantart.net/CsfqTmmnwQAltUe4HYS8A7gsk-s=/300x200/filters:fixed_height(100,100)' +
                ':origin()/pre00/64ea/th/pre/f/2012/125/4/1/static_shock_by_deshockwav-d4ynm1o.png'
            },
            {
              name: 'Jack Jack',
              ability: 'what doesnt he have?',
              organization: 'incredibles',
              image: 'https://i2.wp.com/images4.fanpop.com/image/photos/24000000/JACK-JACK-the-' +
                'incredibles-24099895-344-262.jpg?resize=586%2C390&amp;crop=1'
            }
          ];
    }
}