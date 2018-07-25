import { Component, OnInit, Output, Testability } from '@angular/core';
import { EventEmitter } from 'events';

@Component({
  selector: 'app-avatar-selection',
  templateUrl: './avatar-selection.component.html',
  styleUrls: ['./avatar-selection.component.css']
})
export class AvatarSelectionComponent implements OnInit {
  @Output() dataEvent = new EventEmitter();

  add(namePage: string): void {
      this.dataEvent.emit(namePage);
  }
  constructor() { }

  ngOnInit() {
  }

}
