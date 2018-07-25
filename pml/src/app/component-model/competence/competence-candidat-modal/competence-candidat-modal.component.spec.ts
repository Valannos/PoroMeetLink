import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetenceCandidatModalComponent } from './competence-candidat-modal.component';

describe('CompetenceCandidatModalComponent', () => {
  let component: CompetenceCandidatModalComponent;
  let fixture: ComponentFixture<CompetenceCandidatModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetenceCandidatModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetenceCandidatModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
