export class PaginationModel {
  constructor(public count:number,
              public next:string,
              public previous:string,
              public results:Array<string>
  ) {
  }

}
