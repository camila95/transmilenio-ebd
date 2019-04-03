import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EstacionComponent } from './components/estacion/estacion.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { EstacionEditarComponent } from './components/estacion-editar/estacion-editar.component';
import { EstacionEditarComponent } from './components/estacion-editar/estacion-editar.component';
import { EstacionConsultarComponent } from './components/estacion-consultar/estacion-consultar.component';
import { EstacionEliminarComponent } from './components/estacion-eliminar/estacion-eliminar.component';
import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';
import { TroncalCrearComponent } from './components/troncal-crear/troncal-crear.component';
import { TroncalEditarComponent } from './components/troncal-editar/troncal-editar.component';
import { TroncalConsultarComponent } from './components/troncal-consultar/troncal-consultar.component';
import { TroncalEliminarComponent } from './components/troncal-eliminar/troncal-eliminar.component';

const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'estaciones/crear', component: EstacionComponent },
  { path: 'estaciones/editar', component: EstacionEditarComponent },
  { path: 'estaciones/consultar', component: EstacionConsultarComponent },
  { path: 'estaciones/eliminar', component: EstacionEliminarComponent },
  { path: 'datos/cargararchivo', component: CargarArchivoComponent },
  { path: 'troncal/crear', component: TroncalCrearComponent },
  { path: 'troncal/editar', component: TroncalEditarComponent },
  { path: 'troncal/consultar', component: TroncalConsultarComponent },
  { path: 'troncal/eliminar', component: TroncalEliminarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
