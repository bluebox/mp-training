import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseDialogComponent } from './close-dialog.component';

describe('CloseDialogComponent', () => {
  let component: CloseDialogComponent;
  let fixture: ComponentFixture<CloseDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloseDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CloseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
