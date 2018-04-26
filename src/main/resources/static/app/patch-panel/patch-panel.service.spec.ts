import {inject, TestBed} from '@angular/core/testing';

import {PatchPanelService} from './patch-panel.service';

describe('PatchPanelService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PatchPanelService]
    });
  });

  it('should be created', inject([PatchPanelService], (service: PatchPanelService) => {
    expect(service).toBeTruthy();
  }));
});
