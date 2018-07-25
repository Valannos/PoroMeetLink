import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoroNavComponent } from './poro-nav.component';

describe('PoroNavComponent', () => {
  let component: PoroNavComponent;
  let fixture: ComponentFixture<PoroNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoroNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoroNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
