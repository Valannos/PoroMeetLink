import { Component, OnInit, Input } from '@angular/core';
import { Employe } from '../../../shared/models/employe';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EmployeModalComponent } from '../../employe-modal/employe-modal.component';
import { TokenStorage } from '../../../shared/services/token.storage';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { EmployeComponent } from '../employe.component';
import { EmployeService } from '../../../shared/services/employe.service';
import { AuthenticationService } from '../../../shared/services/authentication.service';

@Component({
  selector: 'app-employe-detail',
  templateUrl: './employe-detail.component.html',
  styleUrls: ['./employe-detail.component.css']
})
export class EmployeDetailComponent implements OnInit {

  constructor(private modalService: NgbModal, private auth: AuthenticationService, private service: EmployeService) { }

  @Input() employe: Employe;
  @Input() utilisateur: Utilisateur;
  refModale: NgbModalRef;

  ngOnInit() {
    if (!this.employe) {
      if (this.auth.isEmploye) {
        this.service.getData().subscribe(empl => this.employe = empl);
      }
    }
  }

  onEdit() {

    this.refModale = this.modalService.open(EmployeModalComponent, { size: 'lg' });
    this.refModale.componentInstance.isEdition = true;
    this.refModale.componentInstance.employe = this.employe;
    this.refModale.result.then(res => {
      if (res) {
        this.employe = res;
        this.service.setData(res);
      }
    });
  }

}
