import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TroncalConsultarComponent } from './troncal-consultar.component';

describe('TroncalConsultarComponent', () => {
  let component: TroncalConsultarComponent;
  let fixture: ComponentFixture<TroncalConsultarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TroncalConsultarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TroncalConsultarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
