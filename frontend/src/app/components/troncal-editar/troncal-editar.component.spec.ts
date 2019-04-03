import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TroncalEditarComponent } from './troncal-editar.component';

describe('TroncalEditarComponent', () => {
  let component: TroncalEditarComponent;
  let fixture: ComponentFixture<TroncalEditarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TroncalEditarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TroncalEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
