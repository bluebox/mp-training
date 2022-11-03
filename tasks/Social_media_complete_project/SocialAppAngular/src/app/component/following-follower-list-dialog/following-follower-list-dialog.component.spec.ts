import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FollowingFollowerListDialogComponent } from './following-follower-list-dialog.component';

describe('FollowingFollowerListDialogComponent', () => {
  let component: FollowingFollowerListDialogComponent;
  let fixture: ComponentFixture<FollowingFollowerListDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FollowingFollowerListDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(FollowingFollowerListDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
