import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetenceModalComponent } from './competence-modal.component';

describe('CompetenceModalComponent', () => {
  let component: CompetenceModalComponent;
  let fixture: ComponentFixture<CompetenceModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetenceModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetenceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
