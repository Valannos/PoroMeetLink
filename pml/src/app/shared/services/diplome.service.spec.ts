/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DiplomeService } from './diplome.service';

describe('Service: DiplomeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiplomeService]
    });
  });

  it('should ...', inject([DiplomeService], (service: DiplomeService) => {
    expect(service).toBeTruthy();
  }));
});
