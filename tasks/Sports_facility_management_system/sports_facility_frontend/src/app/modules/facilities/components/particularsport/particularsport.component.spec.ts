import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticularsportComponent } from './particularsport.component';

describe('ParticularsportComponent', () => {
  let component: ParticularsportComponent;
  let fixture: ComponentFixture<ParticularsportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticularsportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticularsportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
