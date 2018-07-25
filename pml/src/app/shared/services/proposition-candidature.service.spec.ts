import { TestBed, inject } from '@angular/core/testing';

import { PropositionCandidatureService } from './proposition-candidature.service';

describe('PropositionCandidatureService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PropositionCandidatureService]
    });
  });

  it('should be created', inject([PropositionCandidatureService], (service: PropositionCandidatureService) => {
    expect(service).toBeTruthy();
  }));
});
