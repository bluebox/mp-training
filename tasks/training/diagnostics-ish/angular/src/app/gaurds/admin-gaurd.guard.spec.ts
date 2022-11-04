import { TestBed } from '@angular/core/testing';

import { AdminGaurdGuard } from './admin-gaurd.guard';

describe('AdminGaurdGuard', () => {
  let guard: AdminGaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminGaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
