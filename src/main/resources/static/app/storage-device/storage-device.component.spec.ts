import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StorageDeviceComponent} from './storage-device.component';

describe('StorageDeviceComponent', () => {
  let component: StorageDeviceComponent;
  let fixture: ComponentFixture<StorageDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StorageDeviceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StorageDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
