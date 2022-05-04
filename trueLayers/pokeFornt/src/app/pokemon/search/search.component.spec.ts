import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component';
import {Store} from "@ngrx/store";
import {Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {provideMockStore} from "@ngrx/store/testing";
import {pokemonFeature, PokemonState} from "../store/pokemon.reducer";
import {RouterTestingModule} from "@angular/router/testing";
import {routesTest} from "../../shared/test.constant";
import {MatAutocompleteModule} from "@angular/material/autocomplete";

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;
  const initialState:PokemonState={
    selectedPokemon:null,
    availablePokemons: [],
    shakespeareDescription:null
  };
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchComponent ],
      imports:[MatAutocompleteModule],
      providers: [provideMockStore({initialState,selectors:[{selector:pokemonFeature.selectAvailablePokemons,value:[]}]})
        ,{provide:Router ,useValue:RouterTestingModule.withRoutes(routesTest)},
        FormBuilder],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
