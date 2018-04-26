import {inject, TestBed} from '@angular/core/testing';

import {RackService} from './rack.service';

describe('RackService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RackService]
    });
  });

  it('should be created', inject([RackService], (service: RackService) => {
    expect(service).toBeTruthy();
  }));
});
