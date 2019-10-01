import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage {

  public btnCompany = 'Criar';

  private id;

  constructor(
    private activatedRoute: ActivatedRoute
  ) { }

  ionViewWillEnter() {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.btnCompany = this.isNew() ? 'Criar' : 'Atualizar';
  }

  public isNew() {
    return this.id === 'new';
  }

}
