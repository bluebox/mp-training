import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistributorComponent } from './distributor.component';

describe('DistributorComponent', () => {
  let component: DistributorComponent;
  let fixture: ComponentFixture<DistributorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistributorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistributorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
