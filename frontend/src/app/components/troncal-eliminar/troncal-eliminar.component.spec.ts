import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TroncalEliminarComponent } from './troncal-eliminar.component';

describe('TroncalEliminarComponent', () => {
  let component: TroncalEliminarComponent;
  let fixture: ComponentFixture<TroncalEliminarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TroncalEliminarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TroncalEliminarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
