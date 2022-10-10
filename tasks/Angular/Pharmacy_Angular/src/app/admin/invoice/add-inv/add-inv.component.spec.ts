import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInvComponent } from './add-inv.component';

describe('AddInvComponent', () => {
  let component: AddInvComponent;
  let fixture: ComponentFixture<AddInvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddInvComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddInvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
