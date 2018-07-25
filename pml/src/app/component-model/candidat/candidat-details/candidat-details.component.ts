import {
  Component,
  OnInit,
  Input} from '@angular/core';
import { Candidat } from '../../../shared/models/candidat';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { CandidatService } from '../../../shared/services/candidat.service';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { CandidatModalComponent } from '../candidat-modale/candidat-modale.component';

@Component({
  selector: 'app-candidat-details',
  templateUrl: './candidat-details.component.html',
  styleUrls: ['./candidat-details.component.css']
})
export class CandidatDetailsComponent implements OnInit {
  @Input() public utilisateur: Utilisateur;
  @Input() public candidat: Candidat;
  refModale: NgbModalRef;
  isLoading = false;

  constructor(
    private candidatService: CandidatService,
    private modalService: NgbModal,
    private auth: AuthenticationService
  ) {}

  ngOnInit() {
    if (!this.candidat) {
      if (this.auth.isCandidat) {
        this.isLoading = true;
        this.candidatService.getData().subscribe(data => {
          this.candidat = data;
          this.isLoading = false;
        });
      }
    }
  }
  onEdit() {
    this.refModale = this.modalService.open(CandidatModalComponent, {
      size: 'lg'
    });
    this.refModale.componentInstance.user = this.utilisateur;
    this.refModale.componentInstance.candidat = this.candidat;
    this.refModale.componentInstance.isEdition = true;
    this.refModale.result.then(res => {
      if (res) {
        this.candidat = res;
        this.candidatService.setData(res);
      }
    });
  }
}
