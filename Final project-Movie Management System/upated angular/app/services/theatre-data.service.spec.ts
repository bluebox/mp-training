import { TestBed } from '@angular/core/testing';

import { TheatreDataService } from './theatre-data.service';

describe('TheatreDataService', () => {
  let service: TheatreDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TheatreDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
