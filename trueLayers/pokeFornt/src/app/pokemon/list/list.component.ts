import {Component, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs";
import {select, Store} from "@ngrx/store";
import {pokemonFeature} from "../store/pokemon.reducer";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";
import {loadSelectedPokemon} from "../store/pokemon.action";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  availablePokemons$:Observable<Array<string>>;

  displayedColumns: string[] = [ 'name','search'];
  dataSource = new MatTableDataSource<string>();
  @ViewChild(MatPaginator,{ static: true } )paginator: MatPaginator;
  @ViewChild(MatSort,{ static: true }) sort: MatSort;
  constructor(private store:Store,private router:Router) { }

  ngOnInit(): void {
    this.availablePokemons$=this.store.select(pokemonFeature.selectAvailablePokemons);
    this.store.pipe(select(pokemonFeature.selectAvailablePokemons)).subscribe((data)=>{
      this.dataSource.sort=this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.data=data;
    });

  }
  searchPokemon(pokemonName:string){
    this.store.dispatch(loadSelectedPokemon({name:pokemonName}));
    this.router.navigate(['/','pokemon','detail'])
  }
}
