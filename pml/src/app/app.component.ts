import { Component, OnDestroy } from '@angular/core';
import { AuthenticationService } from './shared/services/authentication.service';
import { TokenStorage } from './shared/services/token.storage';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {

  title = 'app';
  suscription: Subscription;

  constructor(private auth: AuthenticationService, private storage: TokenStorage) {
    if (this.storage.getCurrentToken()) {
   this.suscription = auth.getCurrentUtilisateur().subscribe(user =>
        auth.setCurrentUtilisateur(user)
      );
    }
  }

  ngOnDestroy(): void {
   this.suscription.unsubscribe();
  }
}
