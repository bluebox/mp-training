import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitingDialogComponent } from './waiting-dialog.component';

describe('WaitingDialogComponent', () => {
  let component: WaitingDialogComponent;
  let fixture: ComponentFixture<WaitingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WaitingDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(WaitingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
