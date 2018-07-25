import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComptenceAnnonceModalComponent } from './comptence-annonce-modal.component';

describe('ComptenceAnnonceModalComponent', () => {
  let component: ComptenceAnnonceModalComponent;
  let fixture: ComponentFixture<ComptenceAnnonceModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComptenceAnnonceModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComptenceAnnonceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
