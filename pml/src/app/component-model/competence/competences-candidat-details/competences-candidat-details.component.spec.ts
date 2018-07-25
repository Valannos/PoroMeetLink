import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetencesCandidatDetailsComponent } from './competences-candidat-details.component';

describe('CompetencesCandidatDetailsComponent', () => {
  let component: CompetencesCandidatDetailsComponent;
  let fixture: ComponentFixture<CompetencesCandidatDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetencesCandidatDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetencesCandidatDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
