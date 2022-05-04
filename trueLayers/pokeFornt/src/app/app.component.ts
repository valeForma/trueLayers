import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {loadAvailablePokemons} from "./pokemon/store/pokemon.action";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements  OnInit{
  constructor(private store:Store,private router:Router){}
  title = 'Acchiapa A Pikachu';
  ngOnInit(): void {
    this.store.dispatch(loadAvailablePokemons({offset:0,limit:1000}))
  }
  navigateTo(path:string){
    this.router.navigate(['/','pokemon',path]);
  }
}
