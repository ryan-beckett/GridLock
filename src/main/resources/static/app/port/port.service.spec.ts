import {inject, TestBed} from '@angular/core/testing';

import {PortService} from './port.service';

describe('PortService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PortService]
    });
  });

  it('should be created', inject([PortService], (service: PortService) => {
    expect(service).toBeTruthy();
  }));
});
