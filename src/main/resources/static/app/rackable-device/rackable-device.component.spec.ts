import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RackableDeviceComponent} from './rackable-device.component';

describe('RackableDeviceComponent', () => {
  let component: RackableDeviceComponent;
  let fixture: ComponentFixture<RackableDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RackableDeviceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RackableDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
