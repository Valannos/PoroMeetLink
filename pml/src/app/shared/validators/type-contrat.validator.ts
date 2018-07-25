import { ValidatorFn, AbstractControl } from '@angular/forms';
import { TypeContrat } from '../models/annonce';

export function ValidateTypeContrat(): ValidatorFn {
  const typesContrat = Object.keys(TypeContrat);
  return (c: AbstractControl): {[key: string]: boolean} | null => {
    if (c.value) {
      if (!typesContrat.some(val => c.value === val)) {
        return {'invalid_enum': true};
      }
    }
    return null;
  };
}
