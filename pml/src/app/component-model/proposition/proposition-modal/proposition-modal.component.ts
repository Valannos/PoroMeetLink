import { Component, OnInit, Input } from '@angular/core';
import { PropositionCandidature } from '../../../shared/models/proposition.candidature';
import { Annonce } from '../../../shared/models/annonce';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Candidat } from '../../../shared/models/candidat';
import { PropositionCandidatureService } from '../../../shared/services/proposition-candidature.service';

@Component({
  selector: 'app-proposition-modal',
  templateUrl: './proposition-modal.component.html',
  styleUrls: ['./proposition-modal.component.css']
})
export class PropositionModalComponent implements OnInit {

  constructor(private active: NgbActiveModal,
              private builder: FormBuilder,
              private service: PropositionCandidatureService) { }


  public proposition: PropositionCandidature;
  @Input()
  public candidat: Candidat;
  @Input()
  public annonce: Annonce;
  @Input()
  public isCandidatAuteur: boolean;
  public valideButtonLabel = 'Valider';
  public propForm: FormGroup;
  public showError = false;

  ngOnInit() {
    this.proposition = new PropositionCandidature();
    this.proposition.idAnnonce = this.annonce.id;
    this.proposition.idCandidat = this.candidat.id;
    this.proposition.auteurCandidat = this.isCandidatAuteur;
    this.initForm();
  }

  private initForm() {
    this.propForm = this.builder.group({
      accroche: ['', Validators.required],
    });
  }

  public onSubmitForm(): void {

   this.proposition.accroche = this.propForm.get('accroche').value;
   this.service.post(this.proposition).subscribe((res) => {
    this.active.close(res);
   }, (err) => this.showError = true);
  }
  public close(): void {
    this.active.close();
  }
}
