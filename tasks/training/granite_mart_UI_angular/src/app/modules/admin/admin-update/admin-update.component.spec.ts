import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateComponent } from './admin-update.component';

describe('AdminUpdateComponent', () => {
  let component: AdminUpdateComponent;
  let fixture: ComponentFixture<AdminUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
