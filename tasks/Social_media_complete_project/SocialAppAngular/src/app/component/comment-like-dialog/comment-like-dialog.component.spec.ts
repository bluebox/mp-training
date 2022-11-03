import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentLikeDialogComponent } from './comment-like-dialog.component';

describe('CommentLikeDialogComponent', () => {
  let component: CommentLikeDialogComponent;
  let fixture: ComponentFixture<CommentLikeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CommentLikeDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CommentLikeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
