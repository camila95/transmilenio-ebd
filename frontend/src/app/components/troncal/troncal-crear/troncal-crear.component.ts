import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-troncal-crear',
  templateUrl: './troncal-crear.component.html',
  styleUrls: ['./troncal-crear.component.css']
})
export class TroncalCrearComponent implements OnInit {
operadores = ['operador1', 'operador2','operador3'];
  constructor() { }

  ngOnInit() {
  }

}
