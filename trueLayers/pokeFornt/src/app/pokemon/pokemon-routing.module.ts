import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ListComponent} from "./list/list.component";
import {DetailComponent} from "./detail/detail.component";
import {PokemonComponent} from "./pokemon.component";
import {SearchComponent} from "./search/search.component";

const routes: Routes=[
  {path:'list', component:ListComponent},
  {path:'detail', component:DetailComponent},
  {path:'search', component:SearchComponent},
  {path:'', component:SearchComponent}
];

@NgModule({
  imports:[RouterModule.forChild(routes)],
  exports:[RouterModule]
})
export class PokemonRoutingModule{}
