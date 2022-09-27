import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerregisterComponent } from './ownerregister.component';

describe('OwnerregisterComponent', () => {
  let component: OwnerregisterComponent;
  let fixture: ComponentFixture<OwnerregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerregisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
