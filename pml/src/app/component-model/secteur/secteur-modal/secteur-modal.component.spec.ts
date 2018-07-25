import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecteurModalComponent } from './secteur-modal.component';

describe('SecteurModalComponent', () => {
  let component: SecteurModalComponent;
  let fixture: ComponentFixture<SecteurModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecteurModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecteurModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
