import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecteurComboboxComponent } from './secteur-combobox.component';

describe('SecteurComboboxComponent', () => {
  let component: SecteurComboboxComponent;
  let fixture: ComponentFixture<SecteurComboboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecteurComboboxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecteurComboboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
