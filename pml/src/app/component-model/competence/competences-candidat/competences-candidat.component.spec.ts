import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetencesCandidatComponent } from './competences-candidat.component';

describe('CompetencesCandidatComponent', () => {
  let component: CompetencesCandidatComponent;
  let fixture: ComponentFixture<CompetencesCandidatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetencesCandidatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetencesCandidatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
