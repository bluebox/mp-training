import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { UpdateUserEmail } from 'src/app/model/update-user-email';
import { UpdateUserInfo } from 'src/app/model/update-user-info';
import { UpdateUserPassword } from 'src/app/model/update-user-password';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-example',
  templateUrl: './example.component.html',
  styleUrls: ['./example.component.css']
})
export class ExampleComponent implements OnInit {

  name = 'Gleiton';
  lastName = 'Franco';
  LicNum = '1234567890';
  updateInfoFormGroup!: FormGroup;
  updateEmailFormGroup!: FormGroup;
  updatePasswordFormGroup!: FormGroup;
  submittingForm!: boolean;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
  ) { }
  get updateInfoFirstName() { return this.updateInfoFormGroup.get('firstName') }
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



  // matchPasswords: ValidatorFn = (group: FormGroup): ValidationErrors | null => {
  // 	const password = group.get('password').value;
  // 	const passwordRepeat = group.get('passwordRepeat').value;
  // 	return password === passwordRepeat ? null : { passwordMissMatch: true }
  // }

  ngOnInit(): void {
    this.updateInfoFormGroup = this.formBuilder.group({
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
    });
  }



  handleUpdateInfo(): void {
    // 		this.submittingForm = true;
    // 		const updateUserInfo = new UpdateUserInfo();
    // 		updateUserInfo.firstName = this.updateInfoFirstName?.value;
    // 		updateUserInfo.lastName = this.updateInfoLastName?.value;
    // 		updateUserInfo.intro = this.updateInfoIntro?.value;
    // 		updateUserInfo.gender = this.updateInfoGender?.value;
    // 		updateUserInfo.hometown = this.updateInfoHometown?.value;
    // 		updateUserInfo.currentCity = this.updateInfoCurrentCity?.value;
    // 		updateUserInfo.eduInstitution = this.updateInfoEduInstitution?.value;
    // 		updateUserInfo.workplace = this.updateInfoWorkplace?.value;
    // 		updateUserInfo.countryName = this.updateInfoCountryName?.value;
    // 		updateUserInfo.birthDate = moment(this.updateInfoBirthDate?.value).format('YYYY-MM-DD HH:mm:ss').toString();

    // 		// this.subscriptions.push(
    // 			this.userService.updateUserInfo(updateUserInfo).subscribe({
    // 				next: (updatedUser: any) => {
    // 					// this.authService.storeAuthUserInCache(updatedUser);
    // 					// this.matSnackbar.openFromComponent(SnackbarComponent, {
    // 					// 	data: 'Your account has been updated successfully.',
    // 					// 	panelClass: ['bg-success'],
    // 					// 	duration: 5000
    // 					// });
    // 					this.submittingForm = false;
    // 					this.router.navigateByUrl('/profile');
    // 				},
    // 				error: (errorResponse: HttpErrorResponse) => {
    // 					const validationErrors = errorResponse.error.validationErrors;
    // 					if (validationErrors != null) {
    // 						Object.keys(validationErrors).forEach(key => {
    // 							const formControl = this.updateInfoFormGroup.get(key);
    // 							if (formControl) {
    // 								formControl.setErrors({
    // 									serverError: validationErrors[key]
    // 								});
    // 							}
    // 						});
    // 					} else {
    // 						// this.matSnackbar.openFromComponent(SnackbarComponent, {
    // 						// 	data: AppConstants.snackbarErrorContent,
    // 						// 	panelClass: ['bg-danger'],
    // 						// 	duration: 5000
    // 						// });
    //             alert("error occured")
    // 					}
    // 					this.submittingForm = false;
    // 				}
    // 			})
    // 		// );
    // 	}





    // 	handleUpdateEmail(): void {
    // 		this.submittingForm = true;
    // 		const updateUserEmail = new UpdateUserEmail();
    // 		updateUserEmail.email = this.updateEmailFormGroup.get('email')?.value
    // 		updateUserEmail.password = this.updateEmailFormGroup.get('password')?.value;

    // 		// this.subscriptions.push(
    // 			this.userService.updateUserEmail(updateUserEmail).subscribe({
    // 				next: (result: any) => {
    // 					// localStorage.setItem(AppConstants.messageTypeLabel, AppConstants.successLabel);
    // 					// localStorage.setItem(AppConstants.messageHeaderLabel, AppConstants.emailChangeSuccessHeader);
    // 					// localStorage.setItem(AppConstants.messageDetailLabel, AppConstants.emailChangeSuccessDetail);
    // 					// localStorage.setItem(AppConstants.toLoginLabel, AppConstants.trueLabel);
    // 					// this.authService.logout();
    // 					this.submittingForm = false;
    // 					this.router.navigateByUrl('/message');
    // 				},
    // 				error: (errorResponse: HttpErrorResponse) => {
    // 					const validationErrors = errorResponse.error.validationErrors;
    // 					if (validationErrors != null) {
    // 						Object.keys(validationErrors).forEach(key => {
    // 							const formControl = this.updateInfoFormGroup.get(key);
    // 							if (formControl) {
    // 								formControl.setErrors({
    // 									serverError: validationErrors[key]
    // 								});
    // 							}
    // 						});
    // 					} else {
    // 						// this.matSnackbar.openFromComponent(SnackbarComponent, {
    // 						// 	data: AppConstants.snackbarErrorContent,
    // 						// 	panelClass: ['bg-danger'],
    // 						// 	duration: 5000
    // 						// });
    //             console.log("error has occured")
    // 					}
    // 					this.submittingForm = false;
    // 				}
    // 			})
    // 		// );
    // 	}





    //   handleUpdatePassword(): void {
    // 		this.submittingForm = true;
    // 		const updateUserPassword = new UpdateUserPassword();
    // 		updateUserPassword.password = this.updatePasswordFormGroup.get('password')?.value;
    // 		updateUserPassword.passwordRepeat = this.updatePasswordFormGroup.get('passwordRepeat')?.value;
    // 		updateUserPassword.oldPassword = this.updatePasswordFormGroup.get('oldPassword')?.value;

    // 		// this.subscriptions.push(
    // 			this.userService.updateUserPassword(updateUserPassword).subscribe({
    // 				next: (result: any) => {
    // 					// localStorage.setItem(AppConstants.messageTypeLabel, AppConstants.successLabel);
    // 					// localStorage.setItem(AppConstants.messageHeaderLabel, AppConstants.passwordChangeSuccessHeader);
    // 					// localStorage.setItem(AppConstants.messageDetailLabel, AppConstants.passwordChangeSuccessDetail);
    // 					// localStorage.setItem(AppConstants.toLoginLabel, AppConstants.trueLabel);
    // 					// this.authService.logout();
    // 					this.submittingForm = false;
    // 					this.router.navigateByUrl('/message');
    // 				},
    // 				error: (errorResponse: HttpErrorResponse) => {
    // 					const validationErrors = errorResponse.error.validationErrors;
    // 					if (validationErrors != null) {
    // 						Object.keys(validationErrors).forEach(key => {
    // 							const formControl = this.updateInfoFormGroup.get(key);
    // 							if (formControl) {
    // 								formControl.setErrors({
    // 									serverError: validationErrors[key]
    // 								});
    // 							}
    // 						});
    // 					} else {
    // 						// this.matSnackbar.openFromComponent(SnackbarComponent, {
    // 						// 	data: AppConstants.snackbarErrorContent,
    // 						// 	panelClass: ['bg-danger'],
    // 						// 	duration: 5000
    // 						// });
    //             alert("error hass occured")
    // 					}
    // 					this.submittingForm = false;
    // 				}
    // 			})
    // 		// );
  }

}
