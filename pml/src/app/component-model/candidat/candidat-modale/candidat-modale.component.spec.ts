import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CandidatModalComponent } from './candidat-modale.component';

describe('ModalInitUserComponent', () => {
  let component: CandidatModalComponent;
  let fixture: ComponentFixture<CandidatModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidatModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
