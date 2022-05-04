import {DescriptionModel} from "./description.model";

export  class PokemonModel{
  constructor(public id:number,
              public name:string,
              public description:Array<DescriptionModel>){

  }
}
