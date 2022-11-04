import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayBranchesComponent } from './display-branches.component';

describe('DisplayBranchesComponent', () => {
  let component: DisplayBranchesComponent;
  let fixture: ComponentFixture<DisplayBranchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayBranchesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayBranchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
