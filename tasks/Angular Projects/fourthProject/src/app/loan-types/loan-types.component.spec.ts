import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanTypesComponent } from './loan-types.component';

describe('LoanTypesComponent', () => {
  let component: LoanTypesComponent;
  let fixture: ComponentFixture<LoanTypesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanTypesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoanTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
