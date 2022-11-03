import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { PostResponse } from 'src/app/model/post-response';
import { UserResponse } from 'src/app/model/user-response';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';
import { environment } from 'src/environments/environment';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { FollowingFollowerListDialogComponent } from '../following-follower-list-dialog/following-follower-list-dialog.component';
import { PhotoUploadDialogComponent } from '../photo-upload-dialog/photo-upload-dialog.component';
import { SnakebarComponent } from '../snakebar/snakebar.component';
import { ViewPhotoDialogComponent } from '../view-photo-dialog/view-photo-dialog.component';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
	authUser: any;
	profileUserId!: number;
	profileUser: any;
	profileUserPostResponses: PostResponse[] = [];
	isProfileViewerOwner: boolean = false;
	viewerFollowsProfileUser: boolean = false;
	resultPage: number = 1;
	resultSize: number = 5;
	hasMoreResult: boolean = true;
	fetchingResult: boolean = false;
	loadingProfile: boolean = false;
	hasNoPost: boolean = false;



	private subscriptions: Subscription[] = [];
	constructor(private authService: AuthService,
		private userService: UserService,
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private matDialog: MatDialog,
		private matSnackbar: MatSnackBar) { }


	ngOnInit(): void {
		if (!this.authService.isUserLoggedIn()) {
			this.router.navigateByUrl('/login');
		} else {
			this.loadingProfile = true;
			this.authUser = localStorage.getItem('authUser');
			this.authService.getuserdata().subscribe(data => {
				console.log(data.email)
				console.log(data)

				this.authUser = data
				console.log(this.authUser)

				this.authService.storeAuthUserInCache(data);
			});

			if (this.activatedRoute.snapshot.paramMap.get('userId') === null) {
				this.isProfileViewerOwner = true;
				this.profileUserId = this.authService.getAuthUserId();

			} else {

				this.profileUserId = Number(this.activatedRoute.snapshot.paramMap.get('userId'));
				const currentUserId = localStorage.getItem('id')

			}

			this.subscriptions.push(
				this.userService.getUserById(this.profileUserId).subscribe({
					next: (foundUserResponse: any) => {
						console.log(foundUserResponse);
						const foundUser: any = foundUserResponse.data;
						console.log(foundUser.id)
						this.viewerFollowsProfileUser = foundUserResponse.follower_exists

						if (!this.authUser) {   //null verification
							this.authService.getuserdata().subscribe(data => {
								this.authUser = data
								if (foundUser.id === this.authUser.id) {
									this.router.navigateByUrl('/profile');
								}



								if (!foundUser.profile_photo) {
									foundUser.profile_photo = environment.defaultProfilePhotoUrl
								}

								if (!foundUser.cover_photo) {
									foundUser.cover_photo = environment.defaultCoverPhotoUrl
								}

								this.profileUser = foundUser;
								console.log(this.profileUser);

								this.loadProfilePosts(1);

								this.loadingProfile = false;
							})
						}
						else {
							if (foundUser.id === this.authUser.id) {
								this.router.navigateByUrl('/profile');
							}



							if (!foundUser.profile_photo) {
								foundUser.profile_photo = environment.defaultProfilePhotoUrl
							}

							if (!foundUser.cover_photo) {
								foundUser.cover_photo = environment.defaultCoverPhotoUrl
							}

							this.profileUser = foundUser;
							console.log(this.profileUser);

							this.loadProfilePosts(1);

							this.loadingProfile = false;
						}


					},
					error: (errorResponse: HttpErrorResponse) => {
						localStorage.setItem(AppConstants.messageTypeLabel, AppConstants.errorLabel);
						localStorage.setItem(AppConstants.messageHeaderLabel, AppConstants.notFoundErrorHeader);
						localStorage.setItem(AppConstants.messageDetailLabel, AppConstants.notFoundErrorDetail);
						localStorage.setItem(AppConstants.toLoginLabel, AppConstants.falseLabel);
						this.loadingProfile = false;
						this.router.navigateByUrl('/message');
					}
				})
			);
		}


	}
	stopPropagation(e: Event): void {
		e.stopPropagation();
	}
	loadProfilePosts(currentPage: number): void {
		if (!this.fetchingResult) {
			this.fetchingResult = true;
			this.subscriptions.push(
				this.userService.getUserPosts(this.profileUserId, currentPage, this.resultSize).subscribe({
					next: (postResponses: PostResponse[] | any) => {
						postResponses.forEach((post: any) => this.profileUserPostResponses.push(post));
						if (postResponses.length <= 0 && this.resultPage === 1) this.hasNoPost = true;
						if (postResponses.length <= 0) this.hasMoreResult = false;
						this.fetchingResult = false;
						this.resultPage++;
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
	}


	openFollowingDialog(user: any): void {
		this.matDialog.open(FollowingFollowerListDialogComponent, {
			data: {
				user,
				type: 'following'
			},
			autoFocus: false,
			minWidth: '400px',
			maxWidth: '500px'
		});
	}
	openFollowerDialog(user: any): void {
		this.matDialog.open(FollowingFollowerListDialogComponent, {
			data: {
				user,
				type: 'follower'
			},
			autoFocus: false,
			minWidth: '400px',
			maxWidth: '500px'
		});
	}

	openViewPhotoDialog(photoUrl: string): void {
		this.matDialog.open(ViewPhotoDialogComponent, {
			data: photoUrl,
			autoFocus: false,
			maxWidth: '1200px'
		});
	}
	openFollowConfirmDialog(userId: number): void {
		const dialogRef = this.matDialog.open(ConfirmationDialogComponent, {
			data: `Do you want to follow ${this.profileUser.first_name + ' ' + this.profileUser.last_name}?`,
			autoFocus: false,
			maxWidth: '500px'
		});

		dialogRef.afterClosed().subscribe(
			(result) => {
				if (result) {
					this.subscriptions.push(
						this.userService.followUser(userId).subscribe({
							next: (response: any) => {
								this.viewerFollowsProfileUser = true;
								this.matSnackbar.openFromComponent(SnakebarComponent, {
									data: `You are following ${this.profileUser.first_name + ' ' + this.profileUser.last_name}.`,
									panelClass: ['bg-success'],
									duration: 5000
								});
								window.location.reload();
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
			}
		);
	}

	openUnfollowConfirmDialog(userId: number): void {
		const dialogRef = this.matDialog.open(ConfirmationDialogComponent, {
			data: `Do you want to stop following ${this.profileUser.first_name + ' ' + this.profileUser.last_name}?`,
			autoFocus: false,
			maxWidth: '500px'
		});

		dialogRef.afterClosed().subscribe(
			(result) => {
				if (result) {
					this.subscriptions.push(
						this.userService.unfollowUser(userId).subscribe({
							next: (response: any) => {
								this.viewerFollowsProfileUser = false;
								this.matSnackbar.openFromComponent(SnakebarComponent, {
									data: `You no longer follow ${this.profileUser.first_name + ' ' + this.profileUser.last_name}.`,
									panelClass: ['bg-success'],
									duration: 5000
								});
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
			}
		);
	}

	openPhotoUploadDialog(e: Event, uploadType: string): void {
		e.stopPropagation();

		let header: string | any;
		if (uploadType === 'profile_photo') {
			header = 'Upload Profile Photo';
		} else if (uploadType === 'cover_photo') {
			header = 'Upload Cover Photo';
		}

		const dialogRef = this.matDialog.open(PhotoUploadDialogComponent, {
			data: { authUser: this.authUser, uploadType, header },
			autoFocus: false,
			minWidth: '300px',
			maxWidth: '900px',
			maxHeight: '500px'
		});

		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				if (uploadType === 'profile_photo') {
					this.profileUser.profile_photo = result.updatedUser.profile_photo;
				} else if (uploadType === 'cover_photo') {
					this.profileUser.cover_photo = result.updatedUser.cover_photo;
				}
			}
		});
	}

	handlePostDeletedEvent(postResponse: PostResponse): any {
		const button = document.getElementById(`profilePost${postResponse.post.id}`);
		if (button != null) {
			// 👉️ button has type HTMLElement here
			button.remove()
		}
	}
}
