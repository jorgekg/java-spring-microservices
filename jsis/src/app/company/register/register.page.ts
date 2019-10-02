import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CompanyService } from 'src/app/core/entities/company/company.service';
import { AppErrorsService } from 'src/app/core/app-errors/app-errors.service';
import { AppStorageService } from 'src/app/core/app-storage/app-storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  public btnCompany = 'Criar';
  public formGroup: FormGroup
  private id;

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private companyService: CompanyService,
    private appErrorsService: AppErrorsService,
    private router: Router,
    private appStorageService: AppStorageService
  ) { }

  public ngOnInit() {
    this.formGroup = this.formBuilder.group({
      companyName: [null, Validators.compose([Validators.required])],
      socialName: [null, Validators.compose([Validators.required])] 
    });
  }

  ionViewWillEnter() {
    this.formGroup.reset();
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.btnCompany = this.isNew() ? 'Criar' : 'Atualizar';
  }

  public isNew() {
    return this.id === 'new';
  }

  public async save() {
    if (this.formGroup.valid) {
      this.formGroup.disable();
      this.btnCompany = 'Aguarde...';
      try {
        const company = await this.companyService.create(this.formGroup.getRawValue()).toPromise();
        const user = this.appStorageService.getUser();
        debugger
        user.companyId = company.id;
        this.appStorageService.setUser(user);
        this.router.navigate(['/company']);
      } catch (err) {
        this.appErrorsService.showError(err);
      }
    } else {
      this.formGroup.markAllAsTouched();
    }
    this.formGroup.enable();
    this.btnCompany = this.isNew() ? 'Criar' : 'Atualizar';
  } 

}
