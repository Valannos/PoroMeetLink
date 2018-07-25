import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceModalComponent } from './annonce-modal.component';

describe('AnnonceModalComponent', () => {
  let component: AnnonceModalComponent;
  let fixture: ComponentFixture<AnnonceModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnonceModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
