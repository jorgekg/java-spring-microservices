import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-validator',
  templateUrl: './validator.component.html',
  styleUrls: ['./validator.component.scss'],
})
export class ValidatorComponent {

  @Input() form;
  @Input() field;

  constructor() { }

  public getMessage() {
    
  }

}
