import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminManageComponent } from './admin-manage.component';

describe('AdminManageComponent', () => {
  let component: AdminManageComponent;
  let fixture: ComponentFixture<AdminManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminManageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
