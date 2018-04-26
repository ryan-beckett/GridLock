import {inject, TestBed} from '@angular/core/testing';

import {HardwareConfigurationService} from './hardware-configuration.service';

describe('HardwareConfigurationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HardwareConfigurationService]
    });
  });

  it('should be created', inject([HardwareConfigurationService], (service: HardwareConfigurationService) => {
    expect(service).toBeTruthy();
  }));
});
