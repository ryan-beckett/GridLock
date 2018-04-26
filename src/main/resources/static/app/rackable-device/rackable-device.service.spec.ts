import {inject, TestBed} from '@angular/core/testing';

import {RackableDeviceService} from './rackable-device.service';

describe('RackableDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RackableDeviceService]
    });
  });

  it('should be created', inject([RackableDeviceService], (service: RackableDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
