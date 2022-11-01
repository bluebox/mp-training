import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagertaskComponent } from './managertask.component';

describe('ManagertaskComponent', () => {
  let component: ManagertaskComponent;
  let fixture: ComponentFixture<ManagertaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagertaskComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagertaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
