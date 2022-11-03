import { TestBed } from '@angular/core/testing';

import { CommonInterceptorInterceptor } from './common-interceptor.interceptor';

describe('CommonInterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      CommonInterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: CommonInterceptorInterceptor = TestBed.inject(CommonInterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
