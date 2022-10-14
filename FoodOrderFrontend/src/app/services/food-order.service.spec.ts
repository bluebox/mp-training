import { TestBed } from '@angular/core/testing';

import { FoodOrderService } from './food-order.service';

describe('FoodOrderService', () => {
  let service: FoodOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
