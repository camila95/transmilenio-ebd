import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarDatosComponent } from './generar-datos.component';

describe('GenerarDatosComponent', () => {
  let component: GenerarDatosComponent;
  let fixture: ComponentFixture<GenerarDatosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenerarDatosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerarDatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
