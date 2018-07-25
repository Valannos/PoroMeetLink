import { Component, OnInit } from '@angular/core';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrls: ['./datepicker.component.css'],
  providers: [NgbDatepickerConfig]
})
export class DatepickerComponent implements OnInit {

  constructor(private config: NgbDatepickerConfig) {
    config.minDate = {year: 1950, month: 1, day: 1};
   }

  ngOnInit() {
  }

}
