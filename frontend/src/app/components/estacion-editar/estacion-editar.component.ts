import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-estacion-editar',
  templateUrl: './estacion-editar.component.html',
  styleUrls: ['./estacion-editar.component.css']
})
export class EstacionEditarComponent implements OnInit {
  listaTipoEstacion: SelectItem[] = [];
  tipoEstacionSelected: string = null;//Estacion
  items: SelectItem[] = [];
  constructor() {
    //realizar consulta
    this.listaTipoEstacion.push({ label: "Seleccionar", value: 0 });
    this.listaTipoEstacion.push({ label: "Cosa1", value: 1 });
    this.listaTipoEstacion.push({ label: "Cosa2", value: 2 });
    for (let i = 0; i < 10000; i++) {
      this.items.push({ label: 'Item ' + i, value: 'Item ' + i });
    }
  }

  ngOnInit() {
  }

}
