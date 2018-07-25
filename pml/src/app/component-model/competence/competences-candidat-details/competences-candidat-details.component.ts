import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { CompetenceCandidat } from '../../../shared/models/competence.candidat';
import {
  NgbRatingConfig,
  NgbProgressbarConfig
} from '@ng-bootstrap/ng-bootstrap';
import { FormControl, Validators } from '@angular/forms';
import { CompetenceCandidatService } from '../../../shared/services/competence.candidat.service';

@Component({
  selector: 'app-competences-candidat-details',
  templateUrl: './competences-candidat-details.component.html',
  styleUrls: ['./competences-candidat-details.component.css'],
  providers: [NgbRatingConfig, NgbProgressbarConfig]
})
export class CompetencesCandidatDetailsComponent implements OnInit {
  constructor(
    private config: NgbRatingConfig,
    private progress: NgbProgressbarConfig,
    private ccService: CompetenceCandidatService
  ) {
    this.config.max = 10;
    progress.striped = true;

  }

  @Input() public competenceCandidat: CompetenceCandidat;
  public control: FormControl;
  public showProgress = false;
  private currentLevel: number;
  @Output()
  public deleteEmitter = new EventEmitter();

  ngOnInit() {
    this.control = new FormControl(this.competenceCandidat.niveau, {
      validators: [Validators.required]
    });
    this.control.valueChanges.subscribe((value) => {
      this.competenceCandidat.niveau = value;
    });
    this.control.disable();
  }

  edit(competenceCandidat: CompetenceCandidat) {
    if (this.control.enabled) {
      console.log(this.currentLevel);
      if (this.currentLevel !== competenceCandidat.niveau) {
        this.progress.animated = true;
        this.showProgress = true;
        this.ccService.update(this.competenceCandidat).subscribe(
          data => {
            this.competenceCandidat = data;
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
      this.currentLevel = this.competenceCandidat.niveau.valueOf();
      this.control.enable();
    }
  }

  delete(cc: CompetenceCandidat): void {
    this.ccService.delete(cc.id).subscribe((res) => {
      if (res) {
        this.deleteEmitter.emit(cc);
      }
    });
  }
}
