import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'booleanToSiNo',
  standalone: true
})
export class BooleanToSiNoPipe implements PipeTransform {

  transform(value:boolean): string {
    return value ? 'Sí' : 'No'
  }
}
