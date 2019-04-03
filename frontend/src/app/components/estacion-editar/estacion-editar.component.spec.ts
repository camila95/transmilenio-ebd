import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstacionEditarComponent } from './estacion-editar.component';

describe('EstacionEditarComponent', () => {
  let component: EstacionEditarComponent;
  let fixture: ComponentFixture<EstacionEditarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstacionEditarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstacionEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
