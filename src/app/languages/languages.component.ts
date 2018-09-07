import { Component, OnInit } from '@angular/core';
import { LanguagesService } from './languages.service';


@Component({
  selector: 'app-languages',
  templateUrl: './languages.component.html',
  styleUrls: ['./languages.component.css']
})
export class LanguagesComponent implements OnInit {

  constructor(public langService : LanguagesService) { }

  languages:any;
  disablebutton="true";
  disableedit="true";
  langName="";

  onAdd(){
    this.disablebutton="false";
  }
  
  editLanguage(lang){
    this.disableedit="false";
    console.log(lang);
    this.langName=lang;

  }

  onSave(language){
    console.log("lan1", language);
  }

  ngOnInit() {

  this.langService.getLanguages().subscribe((response)=>{
    console.log("languages" , response.data);
    this.languages = response.data;
  });

  }

}
