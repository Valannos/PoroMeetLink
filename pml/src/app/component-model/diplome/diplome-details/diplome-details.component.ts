import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Diplome, StatutDiplomeEnum } from '../../../shared/models/diplome';
import { DiplomeService } from '../../../shared/services/Diplome.service';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DiplomeModalComponent } from '../diplome-modal/diplome-modal.component';

@Component({
  selector: 'app-diplome-details',
  templateUrl: './diplome-details.component.html',
  styleUrls: ['./diplome-details.component.css']
})
export class DiplomeDetailsComponent implements OnInit {
  constructor(
    private diplomeService: DiplomeService,
    private auth: AuthenticationService,
    private ctrl: NgbModal
  ) {}

  @Input() public diplome: Diplome;
  @Output() public deleteEmitter = new EventEmitter();
  public diplomeEmun = StatutDiplomeEnum;
  public ref: NgbModalRef;

  ngOnInit() {}

  delete(diplome: Diplome) {
    this.diplomeService.delete(diplome.id).subscribe(res => {
      if (res) {
        this.deleteEmitter.emit(diplome);
      }
    });
  }
  isCandidat(): boolean {
    return this.auth.isCandidat.valueOf();
  }

  edit() {
    this.ref = this.ctrl.open(DiplomeModalComponent, {
      size: 'lg',
      centered: true
    });
    this.ref.componentInstance.isEdition = true;
    this.ref.componentInstance.diplome = this.diplome;
    this.ref.result.then((dipl => {
      if (dipl) {
        this.diplome = dipl;
      }
    }));
  }
}
