import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/service/post.service';
import { environment } from 'src/environments/environment';
import { SnakebarComponent } from '../snakebar/snakebar.component';

@Component({
	selector: 'app-share-confirm-dialog',
	templateUrl: './share-confirm-dialog.component.html',
	styleUrls: ['./share-confirm-dialog.component.css']
})
export class ShareConfirmDialogComponent implements OnInit {
	targetPostId!: number;

	shareFormGroup!: FormGroup;
	creatingShare: boolean = false;
	defaultProfilePhotoUrl = environment.defaultProfilePhotoUrl;

	private subscriptions: Subscription[] = [];

	constructor(
		@Inject(MAT_DIALOG_DATA) public dataPost: Post,
		private thisMatDialogRef: MatDialogRef<ShareConfirmDialogComponent>,
		private router: Router,
		private postService: PostService,
		private formBuilder: FormBuilder,
		private matSnackbar: MatSnackBar
	) { }
	get content() { return this.shareFormGroup.get('content'); }

	ngOnInit(): void {
		this.shareFormGroup = this.formBuilder.group({
			content: new FormControl('', [Validators.maxLength(4096)])
		});
		this.targetPostId = this.dataPost.isTypeShare ? this.dataPost.sharedPost.id : this.dataPost.id;

	}
	createNewPostShare(): void {
		if (!this.creatingShare) {
			this.creatingShare = true;
			this.subscriptions.push(
				this.postService.createPostShare(this.targetPostId, this.content?.value).subscribe({
					next: (newPostShare: Post) => {
						this.thisMatDialogRef.close();
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: 'Post shared successfully.',
							panelClass: ['bg-success'],
							duration: 5000
						});
						this.creatingShare = false;
						this.router.navigateByUrl(`/posts/${newPostShare.id}`);
					},
					error: (errorResponse: HttpErrorResponse) => {
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: AppConstants.snackbarErrorContent,
							panelClass: ['bg-error'],
							duration: 5000
						});
						this.creatingShare = false;
					}
				})
			);
		}
	}
}
