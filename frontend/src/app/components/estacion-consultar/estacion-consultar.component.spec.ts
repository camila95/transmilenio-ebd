import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstacionConsultarComponent } from './estacion-consultar.component';

describe('EstacionConsultarComponent', () => {
  let component: EstacionConsultarComponent;
  let fixture: ComponentFixture<EstacionConsultarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstacionConsultarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstacionConsultarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
