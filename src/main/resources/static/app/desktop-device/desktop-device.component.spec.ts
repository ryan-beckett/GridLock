import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DesktopDeviceComponent} from './desktop-device.component';

describe('DesktopDeviceComponent', () => {
  let component: DesktopDeviceComponent;
  let fixture: ComponentFixture<DesktopDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DesktopDeviceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DesktopDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
