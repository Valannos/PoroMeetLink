import { AuthenticationService } from '../../shared/services/authentication.service';
import { Injectable } from '@angular/core';

export interface AbstractComponent {

 isAdminSite(): Boolean;

 isCandidat(): Boolean;
}
