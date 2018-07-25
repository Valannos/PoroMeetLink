import { Injectable } from '@angular/core';
import { NgbDatepickerI18n, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { I18nService } from './i18n.service';

const I18N_VALUES = {
  fr: {
    weekdays: ['Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa', 'Di'],
    months: [
      'Janvier',
      'Février',
      'Mars',
      'Avril',
      'Mai',
      'Juin',
      'Juillet',
      'Août',
      'Septembre',
      'Octobre',
      'Novembre',
      'Décembre'
    ]
  }
};

@Injectable()
export class I18nDatePickerService extends NgbDatepickerI18n {

  getDayAriaLabel(date: NgbDateStruct): string {
    return date.day + '-' + date.month + '-' + date.year;
  }
  getWeekdayShortName(weekday: number): string {
   return I18N_VALUES[this.i18n.language].weekdays[weekday - 1];
  }
  getMonthShortName(month: number): string {
    return I18N_VALUES[this.i18n.language].months[month - 1];

  }
  getMonthFullName(month: number): string {
    return this.getMonthShortName(month);
  }

  constructor(private i18n: I18nService) {
    super();
  }
}
