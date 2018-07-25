import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-avatar-image',
  template: `
 <h1>Mon premier Component</h1>
 <p>en action</p>
 `,
 styles: [`
 h1 {
     color: red;
 }
`]
})
export class AvatarImageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
