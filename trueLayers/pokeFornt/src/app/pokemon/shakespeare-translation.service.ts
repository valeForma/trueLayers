import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {PokemonModel} from "../shared/pokemon.model";
import {PaginationModel} from "../shared/pagination.model";
import {ShakespeareTranslationModel} from "../shared/shakespeare-translation.model";

@Injectable({
  providedIn: 'root'
})
export class ShakespeareTranslationService {

  private baseUrl=environment.baseURL;
  constructor(private http:HttpClient) {

  }


  public  translateDescription(content:string){
    content=content?.replace(/[\r\n]+/g," ")||"";
    console.log("doing call ");
    return this.http.get<ShakespeareTranslationModel>(`${this.baseUrl}/translate`,
      { observe: 'response' ,
        params: {
          content: content
        }
      });
  }
}
