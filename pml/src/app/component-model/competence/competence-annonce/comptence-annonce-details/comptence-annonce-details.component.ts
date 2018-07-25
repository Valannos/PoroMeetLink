import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CompetenceAnnonce } from '../../../../shared/models/competence.annonce';
import { CompetenceAnnonceService } from '../../../../shared/services/competence.annonce.service';
import { NgbRatingConfig, NgbProgressbarConfig } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, Validators } from '@angular/forms';
import {AuthenticationService} from '../../../../shared/services/authentication.service';

@Component({
  selector: 'app-comptence-annonce-details',
  templateUrl: './comptence-annonce-details.component.html',
  styleUrls: ['./comptence-annonce-details.component.css']
})
export class ComptenceAnnonceDetailsComponent implements OnInit {

  constructor(
    private config: NgbRatingConfig,
    private progress: NgbProgressbarConfig,
    private caService: CompetenceAnnonceService,
    private auth: AuthenticationService
  ) {
    this.config.max = 10;
    progress.striped = true;

  }

  @Output()
  public deleteEmitter = new EventEmitter<CompetenceAnnonce>();
  public control: FormControl;
  public showProgress = false;
  private currentLevel: number;
  @Input()
  competencesAnnonce: CompetenceAnnonce;


  ngOnInit() {
    this.control = new FormControl(this.competencesAnnonce.niveauRequis, {
      validators: [Validators.required]
    });
    this.control.valueChanges.subscribe((value) => {
      this.competencesAnnonce.niveauRequis = value;
    });
    this.control.disable();
  }

  delete(ca: CompetenceAnnonce): void {
    this.caService.delete(ca.id).subscribe((res) => {
      if (res) {
        this.deleteEmitter.emit(ca);
      }
    });
  }

  edit(competenceCandidat: CompetenceAnnonce) {
    if (this.control.enabled) {
      console.log(this.currentLevel);
      if (this.currentLevel !== competenceCandidat.niveauRequis) {
        this.progress.animated = true;
        this.showProgress = true;
        this.caService.update(this.competencesAnnonce).subscribe(
          data => {
            this.competencesAnnonce = data;
          },
          err => {
            console.log(err);
          },
          () => {
            this.showProgress = false;
            this.progress.animated = false;
          }
        );
      }
      this.control.disable();
    } else {
      this.currentLevel = this.competencesAnnonce.niveauRequis.valueOf();
      this.control.enable();
    }
  }

  isEmploye(): Boolean {
    return this.auth.isEmploye;
  }

  isCandidat(): Boolean {
    return this.auth.isCandidat;
  }


}
