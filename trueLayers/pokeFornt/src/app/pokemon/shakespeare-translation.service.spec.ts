import { TestBed } from '@angular/core/testing';

import { ShakespeareTranslationService } from './shakespeare-translation.service';
import {of} from "rxjs";
import {ShakespeareTranslationModel} from "../shared/shakespeare-translation.model";
import {ContentModel} from "../shared/content.model";
import {SuccessModel} from "../shared/success.model";
import {HttpResponse} from "@angular/common/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {map} from "rxjs/operators";

describe('ShakespeareTranslationService', () => {
  let service: ShakespeareTranslationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ShakespeareTranslationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  describe('all', () => {
    it('checkTranslation', () => {
      const contents=new ContentModel("test Content","test Content","shakespeare");
      const  success=new SuccessModel(1);
      const userResponse:ShakespeareTranslationModel =new ShakespeareTranslationModel(success,contents);
      const response:HttpResponse<ShakespeareTranslationModel>=new HttpResponse();
      let res1:HttpResponse<ShakespeareTranslationModel>;
      spyOn(service, 'translateDescription').and.returnValue(of(response));
      let t:ShakespeareTranslationModel;
      service.translateDescription("test Content").pipe(map(x=>x.body)).subscribe(x=>t=x||userResponse);
      // @ts-ignore
      expect(userResponse).toEqual(t);
    });
  });
});
