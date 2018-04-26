import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ConfigurableDeviceComponent} from './configurable-device.component';

describe('ConfigurableDeviceComponent', () => {
  let component: ConfigurableDeviceComponent;
  let fixture: ComponentFixture<ConfigurableDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ConfigurableDeviceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigurableDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
