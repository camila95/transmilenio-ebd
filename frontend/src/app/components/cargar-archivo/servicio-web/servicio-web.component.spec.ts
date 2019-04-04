import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicioWebComponent } from './servicio-web.component';

describe('ServicioWebComponent', () => {
  let component: ServicioWebComponent;
  let fixture: ComponentFixture<ServicioWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServicioWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicioWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
