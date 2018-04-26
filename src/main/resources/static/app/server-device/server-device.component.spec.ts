import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ServerDeviceComponent} from './server-device.component';

describe('ServerDeviceComponent', () => {
  let component: ServerDeviceComponent;
  let fixture: ComponentFixture<ServerDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ServerDeviceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
