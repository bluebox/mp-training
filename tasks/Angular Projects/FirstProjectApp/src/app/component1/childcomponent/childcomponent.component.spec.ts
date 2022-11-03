import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildcomponentComponent } from './childcomponent.component';

describe('ChildcomponentComponent', () => {
  let component: ChildcomponentComponent;
  let fixture: ComponentFixture<ChildcomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildcomponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChildcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
