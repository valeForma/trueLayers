import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule
} from '@angular/common/http/testing';
import { PokemonService } from './pokemon.service';
import {HttpResponse} from "@angular/common/http";
import {map} from "rxjs/operators";
import {of} from "rxjs";
import {PokemonModel} from "../shared/pokemon.model";
import {PaginationModel} from "../shared/pagination.model";

describe('PokemonService', () => {
  let service: PokemonService;
  const pokemon ={
    name:"pippo",
    id:1,
    description:[]
  };
  const pokemon2 ={
    name:"pippo",
    id:1,
    description:[]
  };

  const pagination={
    count:2,
    next:"next_test",
    previous:"prev_test",
    results:['poke1','poke2']
  };
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(PokemonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  describe('all', () => {
    it('checkListPokemon', () => {
      const response:HttpResponse<PaginationModel>=new HttpResponse({body:pagination});
      spyOn(service, 'getAvailablePokemons').and.returnValue(of(response));
      let t:PaginationModel|null;
      service.getAvailablePokemons(0,1000).pipe(map(x=>x.body)).subscribe(x=>t=x);
      // @ts-ignore
      expect(pagination).toEqual(t);

    });
    it('checkPokemon', () => {
      const response:HttpResponse<PokemonModel>=new HttpResponse({body:pokemon});
      spyOn(service, 'getPokemonByName').and.returnValue(of(response));
      let t:PokemonModel|null;
      service.getPokemonByName("pippo").pipe(map(x=>x.body)).subscribe(x=>t=x);
      // @ts-ignore
      expect(pokemon2).toEqual(t);

    });
  });
});
