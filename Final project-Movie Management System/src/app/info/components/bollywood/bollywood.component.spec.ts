import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BollywoodComponent } from './bollywood.component';

describe('BollywoodComponent', () => {
  let component: BollywoodComponent;
  let fixture: ComponentFixture<BollywoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BollywoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BollywoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
