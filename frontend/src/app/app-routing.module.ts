import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';
import { TroncalCrearComponent } from './components/troncal/troncal-crear/troncal-crear.component';
import { TroncalConsultarComponent } from './components/troncal/troncal-consultar/troncal-consultar.component';
import { GenerarDatosComponent } from './components/cargar-archivo/generar-datos/generar-datos.component';
import { ServicioWebComponent } from './components/cargar-archivo/servicio-web/servicio-web.component';
import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';
import { ReporteRutaAlimenComponent } from './components/reportes/ruta-alimen/reporte-ruta-alimen.component';
import { ReporteRutaTroncalComponent } from './components/reportes/ruta-troncal/reporte-ruta-troncal.component';

const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'estacion/administrar/:id', component: EstacionCrearComponent },
  { path: 'estacion/consultar', component: EstacionConsultarComponent },
  { path: 'troncal/crear', component: TroncalCrearComponent },
  { path: 'troncal/consultar', component: TroncalConsultarComponent },

  { path: 'archivo/generardatos', component: GenerarDatosComponent },
  { path: 'archivo/servicioweb', component: ServicioWebComponent },
  { path: 'reporte/ruta-alimen', component: ReporteRutaAlimenComponent },
  { path: 'reporte/ruta-troncal', component: ReporteRutaTroncalComponent },
  { path: 'archivo/cargararchivo', component: CargarArchivoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
