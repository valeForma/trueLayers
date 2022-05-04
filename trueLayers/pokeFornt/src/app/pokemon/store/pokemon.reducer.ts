import {PokemonModel} from "../../shared/pokemon.model";
import {createFeature, createReducer,on} from "@ngrx/store";
import * as PokemonActions from "./pokemon.action";
import {ShakespeareTranslationModel} from "../../shared/shakespeare-translation.model";

export const pokemonFeatureKey='pokemon';

export interface PokemonState {
  selectedPokemon:PokemonModel|null,
  availablePokemons:Array<string>,
  shakespeareDescription:ShakespeareTranslationModel|null
}
 const initialState:PokemonState={
  selectedPokemon:null,
  availablePokemons: [],
   shakespeareDescription:null
 };

export const pokemonFeature=createFeature({
  name:pokemonFeatureKey,
  reducer:createReducer(initialState,
    on(PokemonActions.loadAvailablePokemons,(state) => ({...state})),
    on(PokemonActions.setAvailablePokemons,(state,{availablePokemons}) => ({...state,availablePokemons})),
    on(PokemonActions.loadSelectedPokemon,(state) => ({...state})),
    on(PokemonActions.setSelectedPokemon,(state,{selectedPokemon}) => ({...state,selectedPokemon})),
    on(PokemonActions.loadShakespeareDescription,(state) => ({...state})),
    on(PokemonActions.setShakespeareDescription,(state,{shakespeareDescription}) => ({...state,shakespeareDescription}))
    )

});
export const {
  name,
  selectAvailablePokemons,
  selectPokemonState,
  selectSelectedPokemon,
  selectShakespeareDescription
}=pokemonFeature;
