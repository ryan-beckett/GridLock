import {inject, TestBed} from '@angular/core/testing';

import {ConfigurableDeviceService} from './configurable-device.service';

describe('ConfigurableDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConfigurableDeviceService]
    });
  });

  it('should be created', inject([ConfigurableDeviceService], (service: ConfigurableDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
