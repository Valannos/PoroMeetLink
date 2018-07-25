import { TestBed, inject } from '@angular/core/testing';

import { SecteurService } from './secteur.service';

describe('SecteurService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SecteurService]
    });
  });

  it('should be created', inject([SecteurService], (service: SecteurService) => {
    expect(service).toBeTruthy();
  }));
});
