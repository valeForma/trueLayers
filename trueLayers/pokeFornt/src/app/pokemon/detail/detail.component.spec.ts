import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetailComponent } from './detail.component';
import {select, Store} from "@ngrx/store";
import {of} from "rxjs";
import {MockStore, provideMockStore} from "@ngrx/store/testing";
import {pokemonFeature, PokemonState} from "../store/pokemon.reducer";
import {HttpResponse} from "@angular/common/http";
import {PokemonModel} from "../../shared/pokemon.model";
import {map} from "rxjs/operators";

describe('DetailComponent', () => {
  let component: DetailComponent;
  let fixture: ComponentFixture<DetailComponent>;
  let store: MockStore;
  const pokemon ={
    name:"pippo",
    id:1,
    description:[]
  };
  afterEach(() => {
    store?.resetSelectors();
  });
  const initialState:PokemonState={
    selectedPokemon:null,
    availablePokemons: [],
    shakespeareDescription:null
  };
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailComponent ],
      providers: [ provideMockStore({  initialState,selectors:[
          {selector:pokemonFeature.selectSelectedPokemon,value:pokemon}
        ]} ) ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('checkPokemonFromStore', () => {
    store = TestBed.inject(MockStore);

    let t:PokemonModel|null;
    store.pipe(select(pokemonFeature.selectSelectedPokemon)).subscribe(x=>t=x);
    // @ts-ignore
    expect(pokemon).toEqual(t);

  });
});
