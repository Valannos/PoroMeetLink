import { Component, OnInit, Input } from '@angular/core';
import { Entreprise } from '../../../shared/models/entreprise';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalEntrepriseComponent } from '../modal-entreprise/modal-entreprise.component';
import { TokenStorage } from '../../../shared/services/token.storage';

@Component({
  selector: 'app-entreprise-details',
  templateUrl: './entreprise-details.component.html',
  styleUrls: ['./entreprise-details.component.css']
})
export class EntrepriseDetailsComponent implements OnInit {

  constructor(  private modalService: NgbModal, private storage: TokenStorage) { }

  @Input() entreprise: Entreprise;
  refModale: NgbModalRef;

  ngOnInit() {
  }

  onEdit() {
    this.refModale = this.modalService.open(ModalEntrepriseComponent, {size: 'lg'});
    this.refModale.componentInstance.entreprise = this.entreprise;
    this.refModale.componentInstance.isEdition = true;
    this.refModale.result.then((etp) => {
      if (etp) {
        this.entreprise = etp;
      }
    });
  }

}
