import {PreloadAllModules, RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";

const appRoutes:Routes =[
  {path:'pokemon', loadChildren: () => import('./pokemon/pokemon.module').then(m => m.PokemonModule)},
  {path:'',redirectTo:'pokemon',pathMatch:'full'}
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes,{preloadingStrategy:PreloadAllModules})
  ],
  exports:[
    RouterModule
  ]

})
export class AppRoutingModule { }
