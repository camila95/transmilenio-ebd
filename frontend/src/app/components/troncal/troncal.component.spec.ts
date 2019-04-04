import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TroncalComponent } from './troncal.component';

describe('TroncalComponent', () => {
  let component: TroncalComponent;
  let fixture: ComponentFixture<TroncalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TroncalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TroncalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
