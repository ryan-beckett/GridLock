import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NetworkConnectionComponent} from './network-connection.component';

describe('NetworkConnectionComponent', () => {
  let component: NetworkConnectionComponent;
  let fixture: ComponentFixture<NetworkConnectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NetworkConnectionComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetworkConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
