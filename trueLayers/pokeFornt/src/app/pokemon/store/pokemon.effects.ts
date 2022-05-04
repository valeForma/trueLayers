import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {
  loadAvailablePokemons,
  loadSelectedPokemon,
  loadShakespeareDescription,
  setAvailablePokemons,
  setSelectedPokemon, setShakespeareDescription
} from "./pokemon.action";
import {catchError, map, mergeMap} from "rxjs/operators";
import {EMPTY, from} from "rxjs";
import {PokemonService} from "../pokemon.service";
import {ShakespeareTranslationService} from "../shakespeare-translation.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ErrorMessage} from "../../shared/error-message.model";
import {HttpErrorResponse} from "@angular/common/http";

@Injectable()
export class PokemonEffects {
  loadPokemonByName$=createEffect(()=>
    this.actions$.pipe(
      ofType(loadSelectedPokemon),
      mergeMap((action)=>this.pokemonService.getPokemonByName(action.name).pipe(
        map(response=> response.body),
        map(selectedPokemon=> setSelectedPokemon({selectedPokemon})),
        catchError((error)=>{
          this.showErrorResult(error,"loadSelectedPokemon");
          return EMPTY})
        )
      )
    )
  );
  loadAvailablePokemons$=createEffect(()=>
    this.actions$.pipe(
      ofType(loadAvailablePokemons),
      mergeMap((action)=>this.pokemonService.getAvailablePokemons(action.offset,action.limit).pipe(
        map(response=> response.body?.results||[]),
        map(availablePokemons=> setAvailablePokemons({availablePokemons})),
        catchError((error)=>{
          this.showErrorResult(error,"loadAvailablePokemons");
          return EMPTY})
        )
      )
    )
  );

  loadShakeSpeareDescription$=createEffect(()=>
    this.actions$.pipe(
      ofType(loadShakespeareDescription),
      mergeMap((action)=>this.shakespeareTranslationService.translateDescription(action.content).pipe(
        map(response=> response.body),
        map(shakespeareDescription=> setShakespeareDescription({shakespeareDescription})),
        catchError((error)=>{
          this.showErrorResult(error,"loadShakespeareDescription");
          return EMPTY})
        )
      )
    )
  );

  constructor(
    private actions$:Actions,
    private  pokemonService:PokemonService,
    private shakespeareTranslationService:ShakespeareTranslationService,
    private _snackBar:MatSnackBar
  ){}
   showErrorResult(error:HttpErrorResponse,resource:string){
    let errorMesage:ErrorMessage = error?.error;
    this._snackBar.open(`errore on resource ${resource} due to ${errorMesage?.errorMessage||'unknown error'}`, resource,{duration:3000});
  }
}
