import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostShareDialogComponent } from './post-share-dialog.component';

describe('PostShareDialogComponent', () => {
  let component: PostShareDialogComponent;
  let fixture: ComponentFixture<PostShareDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostShareDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PostShareDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
