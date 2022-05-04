import {createAction, props} from "@ngrx/store";
import {PokemonModel} from "../../shared/pokemon.model";
import {ShakespeareTranslationModel} from "../../shared/shakespeare-translation.model";

export const loadSelectedPokemon=createAction("[[POKEMON] Feature load selectedPokemon",props<{name:string}>());
export const setSelectedPokemon=createAction("[POKEMON]n Feature set selectedPokemon",props<{selectedPokemon:PokemonModel|null}>());
export const loadAvailablePokemons=createAction("[POKEMON] Feature load avaliable Pokemons",props<{offset:number,limit:number}>());
export const setAvailablePokemons=createAction("[POKEMON] Feature set selectedPokemon",props<{availablePokemons:Array<string>}>());
export const loadShakespeareDescription=createAction("[[POKEMON] Feature load ShakespeareDescription",props<{content:string}>());
export const setShakespeareDescription=createAction("[POKEMON]n Feature set ShakespeareDescription",props<{shakespeareDescription:ShakespeareTranslationModel|null}>());

