import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendProposalPageComponent } from './send-proposal-page.component';

describe('SendProposalPageComponent', () => {
  let component: SendProposalPageComponent;
  let fixture: ComponentFixture<SendProposalPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendProposalPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendProposalPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
