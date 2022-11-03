import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { ResetPassword } from 'src/app/model/reset-password';
import { UserService } from 'src/app/service/user.service';
import { SnakebarComponent } from '../snakebar/snakebar.component';

@Component({
	selector: 'app-reset-password',
	templateUrl: './reset-password.component.html',
	styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
	resetPasswordFormGroup!: FormGroup;
	fetchingResult: boolean = false;
	token: string | any;
	uid: any;
	constructor(private userService: UserService,
		private router: Router,
		private formBuilder: FormBuilder,
		private matSnackbar: MatSnackBar,
		private activatedRoute: ActivatedRoute,
		private matDialog: MatDialog) { }

	private subscriptions: Subscription[] = [];



	get password() { return this.resetPasswordFormGroup.get('password') }
	get passwordRepeat() { return this.resetPasswordFormGroup.get('passwordRepeat') }
	ngOnInit(): void {
		this.token = this.activatedRoute.snapshot.paramMap.get('token');
		this.uid = this.activatedRoute.snapshot.paramMap.get('uid');

		this.resetPasswordFormGroup = this.formBuilder.group({
			password: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(32)]),
			passwordRepeat: new FormControl('', [Validators.required])
		});
	}








	resetPassword(): void {
		if (this.resetPasswordFormGroup.valid) {
			if (!this.fetchingResult) {
				this.fetchingResult = true;
				const resetPassword = new ResetPassword();
				resetPassword.password = this.password?.value;
				resetPassword.password2 = this.passwordRepeat?.value;

				this.subscriptions.push(
					this.userService.resetPassword(this.token, this.uid, resetPassword).subscribe({
						next: (result: any) => {
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: 'Your password has been changed successfully.',
								panelClass: ['bg-success'],
								duration: 5000
							});
							this.fetchingResult = false;
							this.router.navigateByUrl('/login');
						},
						error: (errorResponse: HttpErrorResponse) => {
							this.fetchingResult = false;
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: AppConstants.snackbarErrorContent,
								panelClass: ['bg-danger'],
								duration: 5000
							});
						}
					})
				);
			}
		}
	}

}
