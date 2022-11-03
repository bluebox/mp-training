import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserLogin } from 'src/app/model/user-login';
import { AuthService } from 'src/app/service/auth.service';
import { ForgotPasswordDialogComponent } from '../forgot-password-dialog/forgot-password-dialog.component';
import { SnakebarComponent } from '../snakebar/snakebar.component';
import { Subscription } from 'rxjs';
@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	private subscriptions: Subscription[] = [];

	loginFormGroup!: FormGroup;
	submittingForm: boolean = false;
	constructor(private formBuilder: FormBuilder,
		private router: Router,
		private matSnackbar: MatSnackBar,
		private authService: AuthService,
		private matDialog: MatDialog) { }
	get email() { return this.loginFormGroup.get('email') }
	get password() { return this.loginFormGroup.get('password') }
	ngOnInit(): void {
		this.loginFormGroup = this.formBuilder.group({
			email: new FormControl('',
				[Validators.required, Validators.email]
			),
			password: new FormControl('',
				[Validators.required, Validators.minLength(4), Validators.maxLength(32)]
			)
		});
	}
	ngOnDestroy(): void {
		this.subscriptions.forEach(sub => sub.unsubscribe());
	}
	handleLogin(): void {
		if (this.loginFormGroup.valid) {
			this.submittingForm = true;
			const userLogin = new UserLogin();
			userLogin.email = this.email?.value;
			userLogin.password = this.password?.value;
			console.log(userLogin)
			this.subscriptions.push(
				this.authService.login(userLogin).subscribe({
					next: (response: any) => {
						console.log(response)
						const authToken = response.token.access
						const authRefreshToken = response.token.refresh
						console.log(authToken)
						console.log("user_id", response.body.id)
						localStorage.setItem('email', response.body.email)
						localStorage.setItem('id', response.body.id)
						this.authService.storeTokenInCache(authToken);
						this.authService.storeRefreshTokenInCache(authRefreshToken);
						this.authService.storeAuthUserInCache(response.body);
						this.submittingForm = false;
						this.router.navigate(['/'])
						setTimeout(() => {
							window.location.reload();
						}, 10)

					},
					error: (errorResponse: HttpErrorResponse) => {
						const validationErrors = errorResponse.error.validationErrors;
						if (validationErrors != null) {
							Object.keys(validationErrors).forEach(key => {
								const formControl = this.loginFormGroup.get(key);
								if (formControl) {
									formControl.setErrors({
										serverError: validationErrors[key]
									});
								}
							})
						} else {
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: 'Incorrect email or password.',
								panelClass: ['bg-danger'],
								duration: 5000
							});
						}
						this.submittingForm = false;
						console.log("error occured ")
					}
				})
			);
		}
	}
	openForgotPasswordDialog(e: Event): void {
		e.preventDefault();
		this.matDialog.open(ForgotPasswordDialogComponent, {
			autoFocus: true,
			width: '600px'
		});
	}

}
