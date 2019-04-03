import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstacionEliminarComponent } from './estacion-eliminar.component';

describe('EstacionEliminarComponent', () => {
  let component: EstacionEliminarComponent;
  let fixture: ComponentFixture<EstacionEliminarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstacionEliminarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstacionEliminarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
