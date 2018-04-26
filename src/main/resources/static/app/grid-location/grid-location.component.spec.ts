import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GridLocationComponent} from './grid-location.component';

describe('GridLocationComponent', () => {
  let component: GridLocationComponent;
  let fixture: ComponentFixture<GridLocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GridLocationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
