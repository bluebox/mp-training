import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCancellationComponent } from './edit-cancellation.component';

describe('EditCancellationComponent', () => {
  let component: EditCancellationComponent;
  let fixture: ComponentFixture<EditCancellationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCancellationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCancellationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
