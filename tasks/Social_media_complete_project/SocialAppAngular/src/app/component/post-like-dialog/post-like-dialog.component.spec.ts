import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostLikeDialogComponent } from './post-like-dialog.component';

describe('PostLikeDialogComponent', () => {
  let component: PostLikeDialogComponent;
  let fixture: ComponentFixture<PostLikeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostLikeDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PostLikeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
