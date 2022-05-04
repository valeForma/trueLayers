import {Component, OnDestroy, OnInit} from '@angular/core';
import {PokemonModel} from "../../shared/pokemon.model";
import {Observable} from "rxjs";
import {select, Store} from "@ngrx/store";
import {pokemonFeature} from "../store/pokemon.reducer";
import {map} from "rxjs/operators";
import {loadShakespeareDescription, setShakespeareDescription} from "../store/pokemon.action";
import {ShakespeareTranslationModel} from "../../shared/shakespeare-translation.model";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit ,OnDestroy{

  shakespeareBaseLanguage:string="en";
  selectedPokemon:PokemonModel|null;
  versions:Array<string>=[];
  languages:Array<string>=[];
  version:string|null=null;
  language:string|null=null;
  description:string|null;
  shakespeareDescription$:Observable<ShakespeareTranslationModel|null>;
  constructor(private store:Store) { }

  ngOnInit(): void {
    this.store.pipe(select(pokemonFeature.selectSelectedPokemon)).subscribe((pokemon)=>{
      this.selectedPokemon=pokemon;
      this.versions=pokemon?.description.map(x=>x.version).filter((v, i, a) => a.indexOf(v) === i)||[];
      this.languages=pokemon?.description.map(x=>x.language).filter((v, i, a) => a.indexOf(v) === i)||[];
    });
    this.shakespeareDescription$=this.store.select(pokemonFeature.selectShakespeareDescription);
  }
  descriptionSelect(){
    this.description=this.selectedPokemon?.description
      .filter(x=>x.language==this.language)
        .filter(x=>x.version===this.version)
            .map(x=>x.flavor_text)
                .find(x=>x!=null)||"No description for this language/version";
    }
  onLanguageSelect(value:string){
    this.language=value;
    this.descriptionSelect();

  }
  onVersionSelect(value:string){
      this.version=value;
      this.descriptionSelect();
    let content=this.selectedPokemon?.description
      .filter(x=>x.language==this.shakespeareBaseLanguage)
      .filter(x=>x.version===this.version)
      .map(x=>x.flavor_text)
      .find(x=>x!=null)||"No description for this language/version";
      this.store.dispatch(loadShakespeareDescription({content}))
  }
  ngOnDestroy(): void {
    this.store.dispatch(setShakespeareDescription({shakespeareDescription:null}));
  }
}
