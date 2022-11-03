import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShareConfirmDialogComponent } from './share-confirm-dialog.component';

describe('ShareConfirmDialogComponent', () => {
  let component: ShareConfirmDialogComponent;
  let fixture: ComponentFixture<ShareConfirmDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShareConfirmDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ShareConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
