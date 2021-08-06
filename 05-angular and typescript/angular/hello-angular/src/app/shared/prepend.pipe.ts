import { PipeTransform, Pipe } from '@angular/core';

@Pipe({
    name: 'prepend'
})
export class PrependPipe implements PipeTransform {
    temp: string;

    transform(value: string, target: string): string {
        this.temp = '';
        return this.temp.concat(target, value);
    }
}