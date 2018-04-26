import {inject, TestBed} from '@angular/core/testing';

import {MainFrameService} from './main-frame.service';

describe('MainFrameService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MainFrameService]
    });
  });

  it('should be created', inject([MainFrameService], (service: MainFrameService) => {
    expect(service).toBeTruthy();
  }));
});
