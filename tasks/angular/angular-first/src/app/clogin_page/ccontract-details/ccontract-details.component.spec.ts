import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CcontractDetailsComponent } from './ccontract-details.component';

describe('CcontractDetailsComponent', () => {
  let component: CcontractDetailsComponent;
  let fixture: ComponentFixture<CcontractDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CcontractDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CcontractDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
