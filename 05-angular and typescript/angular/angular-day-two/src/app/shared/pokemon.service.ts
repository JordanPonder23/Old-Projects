import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface Pokes {
  'base_experience': number;
  'id': number;
  'name': string;
}

@Injectable()
export class PokemonService {

  private _url = 'https://pokeapi.co/api/v2/pokemon/121/';

  constructor(private httpCli: HttpClient) { }

  /*An Observable is used to publish information to a stream; all
      subscribers are notified when new informations has entered
      the stream. The subscribers' callback functions are then
      invoked.
    Unlike a promise, a stream can have 1 activation, or 0 activations,
    or MANY activations. A promise simply has a single activation.
  */
  retrievePokemon(): Observable<string> {
    return this.httpCli.get<string>(this._url);
  }

  retrievePokemonTwo(): Observable<Pokes> {
    return this.httpCli.get<Pokes>(this._url);
  }
}
