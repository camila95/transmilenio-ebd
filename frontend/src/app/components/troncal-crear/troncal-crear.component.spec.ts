import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TroncalCrearComponent } from './troncal-crear.component';

describe('TroncalCrearComponent', () => {
  let component: TroncalCrearComponent;
  let fixture: ComponentFixture<TroncalCrearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TroncalCrearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TroncalCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
