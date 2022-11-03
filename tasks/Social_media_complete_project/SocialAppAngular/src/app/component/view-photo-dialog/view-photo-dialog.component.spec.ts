import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPhotoDialogComponent } from './view-photo-dialog.component';

describe('ViewPhotoDialogComponent', () => {
  let component: ViewPhotoDialogComponent;
  let fixture: ComponentFixture<ViewPhotoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewPhotoDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ViewPhotoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
