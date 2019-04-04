import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CargarArchivoComponent } from './cargar-archivo.component';

describe('CargarArchivoComponent', () => {
  let component: CargarArchivoComponent;
  let fixture: ComponentFixture<CargarArchivoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CargarArchivoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CargarArchivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
