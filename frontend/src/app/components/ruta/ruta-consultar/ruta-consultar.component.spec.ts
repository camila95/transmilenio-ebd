import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RutaConsultarComponent } from './ruta-consultar.component';

describe('RutaConsultarComponent', () => {
  let component: RutaConsultarComponent;
  let fixture: ComponentFixture<RutaConsultarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RutaConsultarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RutaConsultarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
