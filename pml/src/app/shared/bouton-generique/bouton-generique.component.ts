import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-bouton-generique',
  templateUrl: './bouton-generique.component.html',
  styleUrls: ['./bouton-generique.component.css']
})
export class BoutonGeneriqueComponent implements OnInit {
  @Input()
  namePage:  string ;
  constructor() { }

  ngOnInit() {
  }

}
