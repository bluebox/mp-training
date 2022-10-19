import { TestBed } from '@angular/core/testing';

import { BranchesService } from './branches.service';

describe('BranchesService', () => {
  let service: BranchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BranchesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
