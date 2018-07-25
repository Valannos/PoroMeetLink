import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeModalComponent } from './employe-modal.component';

describe('EmployeModalComponent', () => {
  let component: EmployeModalComponent;
  let fixture: ComponentFixture<EmployeModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
