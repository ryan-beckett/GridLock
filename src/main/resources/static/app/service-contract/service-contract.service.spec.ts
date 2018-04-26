import {inject, TestBed} from '@angular/core/testing';

import {ServiceContractService} from './service-contract.service';

describe('ServiceContractService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServiceContractService]
    });
  });

  it('should be created', inject([ServiceContractService], (service: ServiceContractService) => {
    expect(service).toBeTruthy();
  }));
});
