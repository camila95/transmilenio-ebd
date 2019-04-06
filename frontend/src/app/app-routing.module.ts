import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';
import { TroncalCrearComponent } from './components/troncal/troncal-crear/troncal-crear.component';
import { TroncalConsultarComponent } from './components/troncal/troncal-consultar/troncal-consultar.component';
import { GenerarDatosComponent } from './components/cargar-archivo/generar-datos/generar-datos.component';
import { ServicioWebComponent } from './components/cargar-archivo/servicio-web/servicio-web.component';
import { ReportesComponent } from './components/reportes/reportes.component';
import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';

const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'estacion/administrar', component: EstacionCrearComponent },
  { path: 'estacion/consultar', component: EstacionConsultarComponent },
  { path: 'troncal/crear', component: TroncalCrearComponent },
  { path: 'troncal/consultar', component: TroncalConsultarComponent },

  { path: 'archivo/generardatos', component: GenerarDatosComponent },
  { path: 'archivo/servicioweb', component: ServicioWebComponent },
  { path: 'reportes', component: ReportesComponent },
  { path: 'archivo/cargararchivo', component: CargarArchivoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
