import { ComponentFixture, TestBed } from '@angular/core/testing';
import 'zone.js';
import 'zone.js/testing';
import { ListComponent } from './list.component';
import {Router} from "@angular/router";
import {pokemonFeature, PokemonState} from "../store/pokemon.reducer";
import {RouterTestingModule} from "@angular/router/testing";
import {routesTest} from "../../shared/test.constant";
import {BrowserDynamicTestingModule, platformBrowserDynamicTesting} from "@angular/platform-browser-dynamic/testing";
import {MockStore, provideMockStore} from "@ngrx/store/testing";

describe('ListComponent', () => {
  let store: MockStore;
  let component: ListComponent;
  let fixture: ComponentFixture<ListComponent>;
  const initialState:PokemonState={
    selectedPokemon:null,
    availablePokemons: [],
    shakespeareDescription:null
  };
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListComponent ],
      providers: [ provideMockStore({initialState,selectors:[{selector:pokemonFeature.selectAvailablePokemons,value:[]}]})
        ,{provide:Router ,useValue:RouterTestingModule.withRoutes(routesTest)} ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
