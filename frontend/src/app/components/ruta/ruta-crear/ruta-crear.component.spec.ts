import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RutaCrearComponent } from './ruta-crear.component';

describe('RutaCrearComponent', () => {
  let component: RutaCrearComponent;
  let fixture: ComponentFixture<RutaCrearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RutaCrearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RutaCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
