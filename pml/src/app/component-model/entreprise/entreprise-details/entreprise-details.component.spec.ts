import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrepriseDetailsComponent } from './entreprise-details.component';

describe('EntrepriseDetailsComponent', () => {
  let component: EntrepriseDetailsComponent;
  let fixture: ComponentFixture<EntrepriseDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EntrepriseDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrepriseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
