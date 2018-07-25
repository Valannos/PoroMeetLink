import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoutonGeneriqueComponent } from './bouton-generique.component';

describe('BoutonGeneriqueComponent', () => {
  let component: BoutonGeneriqueComponent;
  let fixture: ComponentFixture<BoutonGeneriqueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoutonGeneriqueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoutonGeneriqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
