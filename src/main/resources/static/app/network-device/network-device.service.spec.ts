import {inject, TestBed} from '@angular/core/testing';

import {NetworkDeviceService} from './network-device.service';

describe('NetworkDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NetworkDeviceService]
    });
  });

  it('should be created', inject([NetworkDeviceService], (service: NetworkDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
