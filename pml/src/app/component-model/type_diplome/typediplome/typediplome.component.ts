import { Component, OnInit } from '@angular/core';
import { TypeDiplome } from '../../../shared/models/type.diplome';
import { TypeDiplomeService } from '../../../shared/services/type.diplome.service';
import { TokenStorage } from '../../../shared/services/token.storage';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TypeDiplomeModalComponent } from './type.diplome.modal/type.diplome.modal.component';

@Component({
  selector: 'app-typediplome',
  templateUrl: './typediplome.component.html',
  styleUrls: ['./typediplome.component.css']
})
export class TypeDiplomeComponent implements OnInit {
  constructor(
    private typeService: TypeDiplomeService,
    private token: TokenStorage,
    private auth: AuthenticationService,
    private modale: NgbModal
  ) {}

  public types: TypeDiplome[];
  private ref: NgbModalRef;
  ngOnInit() {
    this.typeService.getAll().subscribe((types) => this.types = types);
  }

  public isAdministrateurSite(): Boolean {
    return this.auth.isAdminSite;
  }

  public edit(type: TypeDiplome): void {
    this.ref = this.modale.open(TypeDiplomeModalComponent, {
      size: 'lg',
      centered: true,
      windowClass: 'dark-modal'
    });
    this.ref.componentInstance.isEdition = true;
    this.ref.componentInstance.type = type;
    this.ref.result.then(data => {
      const index: number = this.types.findIndex(
        updated => updated.id === data.id
      );
      this.types[index] = data;
    }).catch((err) => console.log(err));
  }

  public delete(id: number): void {
    this.typeService
      .delete(id)
      .subscribe(
        res => (this.types = this.types.filter(type => type.id !== id))
      );
  }

  public open() {
    this.ref = this.modale.open(TypeDiplomeModalComponent, {
      size: 'lg',
      centered: true,
      windowClass: 'dark-modal'
    });
    this.ref.componentInstance.isEdition = false;
    this.ref.result.then(type => {
      if (type) {
        this.types.push(type);
      }
    }).catch((err) => console.log(err));
  }
}
