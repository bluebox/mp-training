import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateadeviceComponent } from './createadevice.component';

describe('CreateadeviceComponent', () => {
  let component: CreateadeviceComponent;
  let fixture: ComponentFixture<CreateadeviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateadeviceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateadeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
