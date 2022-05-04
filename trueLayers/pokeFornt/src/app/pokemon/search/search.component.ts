import { Component, OnInit } from '@angular/core';
import {select, Store} from "@ngrx/store";
import { pipe } from 'rxjs';
import {pokemonFeature} from "../store/pokemon.reducer";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import {loadSelectedPokemon} from "../store/pokemon.action";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  listaPokemonAutoComplete:string[];
  searchPokemon:FormGroup;
  filteredNames:Observable<string[]>;


  constructor(private store:Store,private formBuilder:FormBuilder,private router:Router) { }

  ngOnInit(): void {
    this.searchPokemon=this.formBuilder.group({
      name:new FormControl('',Validators.required)
    });
    this.store
      .pipe(select(pokemonFeature.selectAvailablePokemons))
      .subscribe((x)=>this.listaPokemonAutoComplete=x);
    this.filteredNames = this.searchPokemon.controls['name'].valueChanges.pipe(
      startWith(''),
      map((value:string) => this._filter(value)),
    );
  }
  private _filter(value: string): string[] {
    if(!value){
      return this.listaPokemonAutoComplete;
    }
    const filterValue = value.toLowerCase();
    return this.listaPokemonAutoComplete.filter(x => x.toLowerCase().includes(filterValue));
  }
  onSubmit(){
    this.store.dispatch(loadSelectedPokemon({...this.searchPokemon.value}));
    this.router.navigate(['/pokemon/detail']);
  }
}
