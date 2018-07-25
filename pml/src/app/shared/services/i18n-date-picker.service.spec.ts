import { TestBed, inject } from '@angular/core/testing';

import { I18nDatePickerService } from './i18n-date-picker.service';

describe('I18nDatePickerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [I18nDatePickerService]
    });
  });

  it('should be created', inject([I18nDatePickerService], (service: I18nDatePickerService) => {
    expect(service).toBeTruthy();
  }));
});
