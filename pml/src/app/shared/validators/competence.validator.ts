import { ValidatorFn, AbstractControl } from '@angular/forms';

/**
 * Effectue un contrôle sur les compétences du candidats : si ce dernier a déjà saisi une CompetenceCandidat contenant une
 * compétence passée en paramètre, on retourne une erreur.
 * @param competenceIds une tableau contenant les id des Competences présente dans les CompetenceCandidat
 */
export function NonDuplicatedCompetenceValidator(competenceIds: number[]): ValidatorFn {
  return (c: AbstractControl): {[key: string]: boolean} | null => {
    if (c.value) {
      if (competenceIds.some(id => c.value.id === id)) {
        return {'duplicated': true};
      }
    }
    return null;
  };
}
