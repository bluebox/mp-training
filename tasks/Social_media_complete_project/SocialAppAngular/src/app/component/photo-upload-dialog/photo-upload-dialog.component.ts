import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';
import { environment } from 'src/environments/environment';
import { SnakebarComponent } from '../snakebar/snakebar.component';

@Component({
	selector: 'app-photo-upload-dialog',
	templateUrl: './photo-upload-dialog.component.html',
	styleUrls: ['./photo-upload-dialog.component.css']
})
export class PhotoUploadDialogComponent implements OnInit {
	photoPreviewUrl!: string | null;
	photo!: File;
	//   data: any;
	defaultProfilePhotoUrl: string = environment.defaultProfilePhotoUrl;
	defaultCoverPhotoUrl: string = environment.defaultCoverPhotoUrl;
	private subscriptions: Subscription[] = [];
	constructor(
		@Inject(MAT_DIALOG_DATA) public data: any,
		private authService: AuthService,
		private userService: UserService,
		private thisDialogRef: MatDialogRef<PhotoUploadDialogComponent>,
		private matSnackbar: MatSnackBar,
		private route: Router,
	) { }

	ngOnInit(): void {
		if (this.data.uploadType === 'profile_photo') {
			this.photoPreviewUrl = this.data.authUser.profile_photo ? this.data.authUser.profile_photo : this.defaultProfilePhotoUrl;
		} else if (this.data.uploadType === 'cover_photo') {
			this.photoPreviewUrl = this.data.authUser.cover_photo ? this.data.authUser.cover_photo : this.defaultCoverPhotoUrl;
		}
	}
	previewPhoto(e: any): void {
		if (e.target.files) {
			this.photo = e.target.files[0];
			const reader = new FileReader();
			reader.readAsDataURL(this.photo);
			reader.onload = (e: any) => {
				this.photoPreviewUrl = e.target.result;
			}
		}
	}

	savePhoto(): void {
		if (this.photo) {
			if (this.data.uploadType === 'profile_photo') {
				this.subscriptions.push(
					this.userService.updateProfilePhoto(this.photo).subscribe({
						next: (updatedUser: any) => {
							this.authService.storeAuthUserInCache(updatedUser);
							this.photoPreviewUrl = null;
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: 'Profile photo updated successfully.',
								panelClass: ['bg-success'],
								duration: 5000
							});
							this.thisDialogRef.close({ updatedUser });
						},
						error: (errorResponse: HttpErrorResponse) => {
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: AppConstants.snackbarErrorContent,
								panelClass: ['bg-danger'],
								duration: 5000
							});
						}
					})
				);
			} else if (this.data.uploadType === 'cover_photo') {
				this.subscriptions.push(
					this.userService.updateCoverPhoto(this.photo).subscribe({
						next: (updatedUser: User) => {
							this.authService.storeAuthUserInCache(updatedUser);
							this.photoPreviewUrl = null;
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: 'Cover photo updated successfully.',
								panelClass: ['bg-success'],
								duration: 5000
							});
							this.thisDialogRef.close({ updatedUser });

						},
						error: (errorResponse: HttpErrorResponse) => {
							this.matSnackbar.openFromComponent(SnakebarComponent, {
								data: AppConstants.snackbarErrorContent,
								panelClass: ['bg-danger'],
								duration: 5000
							});
						}
					})
				);
			}
		} else {
			this.matSnackbar.openFromComponent(SnakebarComponent, {
				data: 'Please, first upload a photo to save.',
				panelClass: ['bg-danger'],
				duration: 5000
			});
		}
	};

}
