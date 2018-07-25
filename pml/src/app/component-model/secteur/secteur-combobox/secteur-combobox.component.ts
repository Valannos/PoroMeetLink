import { Component, OnInit, Input } from '@angular/core';
import { SecteurService } from '../../../shared/services/secteur.service';
import { Secteur } from '../../../shared/models/secteur';

@Component({
  selector: 'app-secteur-combobox',
  templateUrl: './secteur-combobox.component.html',
  styleUrls: ['./secteur-combobox.component.css']
})
export class SecteurComboboxComponent implements OnInit {

  constructor(private service: SecteurService) { }

  @Input() secteurs: Secteur[];

  ngOnInit() {
    if (!this.secteurs) {
      this.service.getAll().subscribe(scts => {
        this.secteurs = scts;
      });
    }
  }

}
