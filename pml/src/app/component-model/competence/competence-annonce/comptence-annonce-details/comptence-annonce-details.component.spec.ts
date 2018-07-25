import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComptenceAnnonceDetailsComponent } from './comptence-annonce-details.component';

describe('ComptenceAnnonceDetailsComponent', () => {
  let component: ComptenceAnnonceDetailsComponent;
  let fixture: ComponentFixture<ComptenceAnnonceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComptenceAnnonceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComptenceAnnonceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
