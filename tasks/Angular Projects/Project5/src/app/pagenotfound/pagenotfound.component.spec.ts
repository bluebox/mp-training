import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PAGENOTFOUNDComponent } from './pagenotfound.component';

describe('PAGENOTFOUNDComponent', () => {
  let component: PAGENOTFOUNDComponent;
  let fixture: ComponentFixture<PAGENOTFOUNDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PAGENOTFOUNDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PAGENOTFOUNDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
