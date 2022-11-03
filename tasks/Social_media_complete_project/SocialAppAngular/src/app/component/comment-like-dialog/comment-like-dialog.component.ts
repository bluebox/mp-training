import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { User } from 'src/app/model/user';
import { CommentService } from 'src/app/service/comment.service';
import { environment } from 'src/environments/environment';
import { SnakebarComponent } from '../snakebar/snakebar.component';
import { Comment } from 'src/app/model/comment';

@Component({
	selector: 'app-comment-like-dialog',
	templateUrl: './comment-like-dialog.component.html',
	styleUrls: ['./comment-like-dialog.component.css']
})
export class CommentLikeDialogComponent implements OnInit {
	likeList: User[] = [];
	resultPage: number = 1;
	resultSize: number = 1;
	hasMoreResult: boolean = false;
	fetchingResult: boolean = false;
	defaultProfilePhotoUrl = environment.defaultProfilePhotoUrl;

	private subscriptions: Subscription[] = [];

	constructor(@Inject(MAT_DIALOG_DATA) public dataComment: Comment,
		private commentService: CommentService,
		private matSnackbar: MatSnackBar) { }

	ngOnInit(): void {
		this.loadCommentLikes(1);
	}
	ngOnDestroy(): void {
		this.subscriptions.forEach(sub => sub.unsubscribe());
	}
	loadCommentLikes(currentPage: number): void {
		if (this.dataComment.likeCount > 0) {
			this.fetchingResult = true;
			this.subscriptions.push(
				this.commentService.getCommentLikes(this.dataComment.id, currentPage, this.resultSize).subscribe({
					next: (resultList: User[]) => {
						resultList.forEach(like => this.likeList.push(like));
						if (currentPage * this.resultSize < this.dataComment.likeCount) {
							this.hasMoreResult = true;
						} else {
							this.hasMoreResult = false;
						}
						this.resultPage++;
						this.fetchingResult = false;
					},
					error: (errorResponse: HttpErrorResponse) => {
						this.matSnackbar.openFromComponent(SnakebarComponent, {
							data: AppConstants.snackbarErrorContent,
							panelClass: ['bg-danger'],
							duration: 5000
						});
						this.fetchingResult = false;
					}
				})
			);
		}
	}
}
