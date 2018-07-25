import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoroFooterComponent } from './poro-footer.component';

describe('PoroFooterComponent', () => {
  let component: PoroFooterComponent;
  let fixture: ComponentFixture<PoroFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoroFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoroFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
