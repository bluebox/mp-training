import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFoodtoMenuComponent } from './add-foodto-menu.component';

describe('AddFoodtoMenuComponent', () => {
  let component: AddFoodtoMenuComponent;
  let fixture: ComponentFixture<AddFoodtoMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFoodtoMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFoodtoMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
