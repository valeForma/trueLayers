import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PokemonModel} from "../shared/pokemon.model";
import {PaginationModel} from "../shared/pagination.model";

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  private baseUrl=environment.baseURL;
  constructor(private http:HttpClient) {

  }
  public  getPokemonByName(name:string){
    return this.http.get<PokemonModel>(`${this.baseUrl}/search/${name}`,{observe:"response"});
  }

  public  getAvailablePokemons(offset:number,limit:number){
    return this.http.get<PaginationModel>(`${this.baseUrl}/list`,
      { observe: 'response' ,
                params: {
                  offset: offset,
                  limit: limit
                }
      });
  }
}
