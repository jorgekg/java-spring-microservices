import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppStorageService } from '../core/app-storage/app-storage.service';
import { AppErrorsService } from '../core/app-errors/app-errors.service';
import { UserService } from '../core/entities/user/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  public formGroup: FormGroup
  public btnLogin = 'Acessar';

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private appErrors: AppErrorsService,
    private appStorageService: AppStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      email: [null, Validators.compose([Validators.required])],
      password: [null, Validators.compose([Validators.required])]
    });
  }

  public async login() {
    if (this.formGroup.valid) {
      this.formGroup.disable();
      this.btnLogin = 'Aguarde...';
      try {
        const token = await this.userService.login(this.formGroup.getRawValue()).toPromise();
        this.appStorageService.setToken(token);
        this.router.navigate(['/home']);
        this.formGroup.reset();
      } catch (err) {
        this.appErrors.showError(err);
      }
    }
    this.formGroup.enable();
    this.btnLogin = 'Acessar';
  }

}
