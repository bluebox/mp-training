import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDemoComponent } from './edit-demo.component';

describe('EditDemoComponent', () => {
  let component: EditDemoComponent;
  let fixture: ComponentFixture<EditDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
