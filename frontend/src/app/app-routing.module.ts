import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EstacionComponent } from './components/estacion/estacion.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';

const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'estaciones/crear', component: EstacionCrearComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
