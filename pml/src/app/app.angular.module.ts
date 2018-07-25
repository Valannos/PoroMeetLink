import { NgModule } from '@angular/core';
import {MatButtonModule, MatInputModule, MatCardModule, MatRadioModule, MatCheckboxModule} from '@angular/material';

@NgModule({
  imports: [ MatButtonModule, MatInputModule, MatCardModule, MatButtonModule, MatRadioModule, MatCheckboxModule ],
  exports: [ MatButtonModule, MatInputModule, MatCardModule, MatButtonModule, MatRadioModule, MatCheckboxModule ]
})
export class AppAngularModule { }
