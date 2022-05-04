import {Routes} from "@angular/router";
import {ListComponent} from "../pokemon/list/list.component";
import {DetailComponent} from "../pokemon/detail/detail.component";
import {SearchComponent} from "../pokemon/search/search.component";

export const routesTest: Routes=[
  {path:'/pokemon/list', component:ListComponent},
  {path:'/pokemondetail', component:DetailComponent},
  {path:'/pokemonsearch', component:SearchComponent},
  {path:'', component:SearchComponent}
];
