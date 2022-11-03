import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { RepeatPasswordMatcher } from 'src/app/common/repeat-password-matcher';
import { Country } from 'src/app/model/country';
import { UpdateUserEmail } from 'src/app/model/update-user-email';
import { UpdateUserInfo } from 'src/app/model/update-user-info';
import { UpdateUserPassword } from 'src/app/model/update-user-password';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { CountryService } from 'src/app/service/country.service';
import { UserService } from 'src/app/service/user.service';
import { SnakebarComponent } from '../snakebar/snakebar.component';

@Component({
	selector: 'app-settings',
	templateUrl: './settings.component.html',
	styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
	authUser: any;
	authUserId!: number;
	submittingForm: boolean = false;
	countryList: any;
	updateInfoFormGroup!: FormGroup;
	updateEmailFormGroup!: FormGroup;
	updatePasswordFormGroup!: FormGroup;
	repeatPasswordMatcher = new RepeatPasswordMatcher();
	private subscriptions: Subscription[] = [];

	constructor(
		private authService: AuthService,
		private formBuilder: FormBuilder,
		private router: Router,
		private countryService: CountryService,

		private userService: UserService,
		private matSnackbar: MatSnackBar,
	) {

	}
	get updateInfoFirstName() { return this.updateInfoFormGroup.get('firstName') }
	get updateInfoUsername() { return this.updateInfoFormGroup.get('Username') }
	get updateInfoLastName() { return this.updateInfoFormGroup.get('lastName') }
	get updateInfoIntro() { return this.updateInfoFormGroup.get('intro') }
	get updateInfoGender() { return this.updateInfoFormGroup.get('gender') }
	get updateInfoHometown() { return this.updateInfoFormGroup.get('hometown') }
	get updateInfoCurrentCity() { return this.updateInfoFormGroup.get('currentCity') }
	get updateInfoEduInstitution() { return this.updateInfoFormGroup.get('eduInstitution') }
	get updateInfoWorkplace() { return this.updateInfoFormGroup.get('workplace') }
	get updateInfoCountryName() { return this.updateInfoFormGroup.get('countryName') }
	get updateInfoBirthDate() { return this.updateInfoFormGroup.get('birthDate') }

	get updateEmailNewEmail() { return this.updateEmailFormGroup.get('email') }
	get updateEmailPassword() { return this.updateEmailFormGroup.get('password') }

	get updatePasswordNewPassword() { return this.updatePasswordFormGroup.get('password') }
	get updatePasswordPasswordRepeat() { return this.updatePasswordFormGroup.get('passwordRepeat') }
	get updatePasswordOldPassword() { return this.updatePasswordFormGroup.get('oldPassword') }



	matchPasswords: any | ValidatorFn = (group: FormGroup): ValidationErrors | null => {
		const password = group.get('password')?.value;
		const passwordRepeat = group.get('passwordRepeat')?.value;
		return password === passwordRepeat ? null : { passwordMissMatch: true }
	}

	ngOnInit(): void {
		this.authUser = this.authService.getAuthUserFromCache();
		this.authService.getuserdata().subscribe(data => {
			this.authUser = data
			console.log(this.authUser)
			const newLoan = {
				'Username': this.authUser.username,
				'firstName': this.authUser.first_name,
				'lastName': this.authUser.last_name,
				"intro": this.authUser.intro,
				"hometown": this.authUser.hometown,
				"currentCity": this.authUser.current_city,
				"eduInstitution": this.authUser.username,
				"workplace": this.authUser.edu_institution,
				"gender": this.authUser.gender,
				"countryName": this.authUser.country,
				"birthDate": this.authUser.birth_date
			}
			this.updateInfoFormGroup.setValue(newLoan)
		})

		this.countryService.getCountryList().subscribe({
			next: (countryList: Country[]) => {
				this.countryList = countryList;
				console.log(this.countryList)
			},
			error: (errorResponse: HttpErrorResponse) => { }
		});
		this.updateInfoFormGroup = this.formBuilder.group({
			Username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
			firstName: new FormControl('', [Validators.required, Validators.maxLength(64)]),
			lastName: new FormControl('', [Validators.required, Validators.maxLength(64)]),
			intro: new FormControl('', [Validators.maxLength(100)]),
			hometown: new FormControl('', [Validators.maxLength(128)]),
			currentCity: new FormControl('', [Validators.maxLength(128)]),
			eduInstitution: new FormControl('', [Validators.maxLength(128)]),
			workplace: new FormControl('', [Validators.maxLength(128)]),
			gender: new FormControl('', [Validators.required]),
			countryName: new FormControl('', [Validators.required]),
			birthDate: new FormControl('', [Validators.required])
		});
		this.updateEmailFormGroup = this.formBuilder.group({
			email: new FormControl("", [Validators.required, Validators.email, Validators.maxLength(64)]),
			password: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(32)])
		});
		this.updatePasswordFormGroup = this.formBuilder.group({
			password: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(32)]),
			passwordRepeat: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(32)]),
			oldPassword: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(32)])
		}, { validators: this.matchPasswords });

	}



	handleUpdateInfo(): void {
		this.submittingForm = true;
		const updateUserInfo = new UpdateUserInfo();
		updateUserInfo.username = this.updateInfoUsername?.value;
		updateUserInfo.first_name = this.updateInfoFirstName?.value;
		updateUserInfo.last_name = this.updateInfoLastName?.value;
		updateUserInfo.intro = this.updateInfoIntro?.value;
		updateUserInfo.gender = this.updateInfoGender?.value;
		updateUserInfo.hometown = this.updateInfoHometown?.value;
		updateUserInfo.current_city = this.updateInfoCurrentCity?.value;
		updateUserInfo.edu_institution = this.updateInfoEduInstitution?.value;
		updateUserInfo.workplace = this.updateInfoWorkplace?.value;
		updateUserInfo.country = this.updateInfoCountryName?.value;
		updateUserInfo.birth_date = moment(this.updateInfoBirthDate?.value).format('YYYY-MM-DD HH:mm:ss').toString();
		console.log(updateUserInfo);

		this.subscriptions.push(
			this.userService.updateUserInfo(updateUserInfo).subscribe({
				next: (updatedUser: any) => {
					this.authService.storeAuthUserInCache(updatedUser);
					this.matSnackbar.openFromComponent(SnakebarComponent, {
						data: 'Your account has been updated successfully.',
						panelClass: ['bg-success'],
						duration: 5000
					});
					this.submittingForm = false;
					this.router.navigateByUrl('/message');
				},
				error: (errorResponse: HttpErrorResponse) => {
					const validationErrors = errorResponse.error.validationErrors;
					if (validationErrors != null) {
						Object.keys(validationErrors).forEach(key => {
							const formControl = this.updateInfoFormGroup.get(key);
							if (formControl) {
								formControl.setErrors({
									serverError: validationErrors[key]
								});
							}
						});
					} else {
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: AppConstants.snackbarErrorContent,
							panelClass: ['bg-danger'],
							duration: 5000
						});
						alert("error occured")
					}
					this.submittingForm = false;
				}
			})
		);
	}





	handleUpdateEmail(): void {
		this.submittingForm = true;
		const updateUserEmail = new UpdateUserEmail();
		updateUserEmail.newEmail = this.updateEmailFormGroup.get('email')?.value
		updateUserEmail.password = this.updateEmailFormGroup.get('password')?.value;

		this.subscriptions.push(
			this.userService.updateUserEmail(updateUserEmail).subscribe({
				next: (result: any) => {
					localStorage.setItem(AppConstants.messageTypeLabel, AppConstants.successLabel);
					localStorage.setItem(AppConstants.messageHeaderLabel, AppConstants.emailChangeSuccessHeader);
					localStorage.setItem(AppConstants.messageDetailLabel, AppConstants.emailChangeSuccessDetail);
					localStorage.setItem(AppConstants.toLoginLabel, AppConstants.trueLabel);
					this.authService.logout();
					this.submittingForm = false;
					this.router.navigateByUrl('/message');
				},
				error: (errorResponse: HttpErrorResponse) => {
					const validationErrors = errorResponse.error.validationErrors;
					if (validationErrors != null) {
						Object.keys(validationErrors).forEach(key => {
							const formControl = this.updateInfoFormGroup.get(key);
							if (formControl) {
								formControl.setErrors({
									serverError: validationErrors[key]
								});
							}
						});
					} else {
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: AppConstants.snackbarErrorContent,
							panelClass: ['bg-danger'],
							duration: 5000
						});
						console.log("error has occured")
					}
					this.submittingForm = false;
				}
			})
		);
	}





	handleUpdatePassword(): void {
		this.submittingForm = true;
		const updateUserPassword = new UpdateUserPassword();
		updateUserPassword.password = this.updatePasswordFormGroup.get('password')?.value;
		updateUserPassword.password2 = this.updatePasswordFormGroup.get('passwordRepeat')?.value;
		updateUserPassword.oldpassword = this.updatePasswordFormGroup.get('oldPassword')?.value;

		this.subscriptions.push(
			this.userService.updateUserPassword(updateUserPassword).subscribe({
				next: (result: any) => {
					localStorage.setItem(AppConstants.messageTypeLabel, AppConstants.successLabel);
					localStorage.setItem(AppConstants.messageHeaderLabel, AppConstants.passwordChangeSuccessHeader);
					localStorage.setItem(AppConstants.messageDetailLabel, AppConstants.passwordChangeSuccessDetail);
					localStorage.setItem(AppConstants.toLoginLabel, AppConstants.trueLabel);
					this.authService.logout();
					this.submittingForm = false;
					this.router.navigateByUrl('/message');
				},
				error: (errorResponse: HttpErrorResponse) => {
					const validationErrors = errorResponse.error.validationErrors;
					if (validationErrors != null) {
						Object.keys(validationErrors).forEach(key => {
							const formControl = this.updateInfoFormGroup.get(key);
							if (formControl) {
								formControl.setErrors({
									serverError: validationErrors[key]
								});
							}
						});
					} else {
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: AppConstants.snackbarErrorContent,
							panelClass: ['bg-danger'],
							duration: 5000
						});
						alert("error hass occured")
					}
					this.submittingForm = false;
				}
			})
		);
	}


}



