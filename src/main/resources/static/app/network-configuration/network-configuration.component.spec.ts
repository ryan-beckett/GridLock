import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NetworkConfigurationComponent} from './network-configuration.component';

describe('NetworkConfigurationComponent', () => {
  let component: NetworkConfigurationComponent;
  let fixture: ComponentFixture<NetworkConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NetworkConfigurationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetworkConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
