import {inject, TestBed} from '@angular/core/testing';

import {GridLocationService} from './grid-location.service';

describe('GridLocationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GridLocationService]
    });
  });

  it('should be created', inject([GridLocationService], (service: GridLocationService) => {
    expect(service).toBeTruthy();
  }));
});
