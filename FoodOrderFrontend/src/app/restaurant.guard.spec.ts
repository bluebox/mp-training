import { TestBed } from '@angular/core/testing';

import { RestaurantGuard } from './restaurant.guard';

describe('RestaurantGuard', () => {
  let guard: RestaurantGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RestaurantGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
