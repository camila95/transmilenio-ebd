import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-generar-datos',
  templateUrl: './generar-datos.component.html',
  styleUrls: ['./generar-datos.component.css']
})
export class GenerarDatosComponent implements OnInit {
tablas = ['tabla1', 'tabla2','tabla3'];
  constructor() { }

  ngOnInit() {
  }

}
