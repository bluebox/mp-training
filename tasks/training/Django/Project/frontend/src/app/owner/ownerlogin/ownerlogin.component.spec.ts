import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerloginComponent } from './ownerlogin.component';

describe('OwnerloginComponent', () => {
  let component: OwnerloginComponent;
  let fixture: ComponentFixture<OwnerloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
