import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreelancerProposalsComponent } from './freelancer-proposals.component';

describe('FreelancerProposalsComponent', () => {
  let component: FreelancerProposalsComponent;
  let fixture: ComponentFixture<FreelancerProposalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreelancerProposalsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreelancerProposalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
