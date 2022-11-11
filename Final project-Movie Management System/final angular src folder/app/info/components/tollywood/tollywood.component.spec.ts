import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TollywoodComponent } from './tollywood.component';

describe('TollywoodComponent', () => {
  let component: TollywoodComponent;
  let fixture: ComponentFixture<TollywoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TollywoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TollywoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
