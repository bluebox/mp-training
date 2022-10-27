import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterHallsComponent } from './alter-halls.component';

describe('AlterHallsComponent', () => {
  let component: AlterHallsComponent;
  let fixture: ComponentFixture<AlterHallsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlterHallsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlterHallsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
