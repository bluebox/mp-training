import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';

export interface DialogData {
  review: string;
  rating: number;
  bid: number;
}
@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css'],
})
export class UserhomeComponent implements OnInit {
  uid: any;
  bookingDetails: any;
  sub1: any;
  feedback: any;
  review: string = '';
  rating!: number;
  user_details: any;
  upcomigbookingsexists: number = 0;
  pastbookingsexists: number = 0;
  editUserDetailsForm: FormGroup = new FormGroup({
    user_name: new FormControl('', [Validators.required]),
    user_email: new FormControl('', [Validators.required, Validators.email]),
  });
  constructor(
    private service: UserService,
    private arouter: ActivatedRoute,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.arouter.params.subscribe((data) => {
      this.uid = data['id'];
      console.log(data);
    });
    this.getuserBookings();
    this.getUserDetails();
  }
  getUserDetails() {
    this.service.getUserDetails(this.uid).subscribe((data) => {
      this.user_details = data;
      console.log(this.user_details.user_name);
      this.editUserDetailsForm
        .get('user_name')
        ?.setValue(this.user_details.user_name);
      this.editUserDetailsForm
        .get('user_email')
        ?.setValue(this.user_details.user_email);
    });
  }

  getuserBookings() {
    this.upcomigbookingsexists = 0;
    this.pastbookingsexists = 0;
    var date = new Date();
    date.setDate(date.getDate());
    const month = date.toLocaleString('default', { month: 'long' });
    const day = date.getDate();
    const year = date.getFullYear();
    const hour = date.getHours();
    const today = day + ',' + month + ',' + year;
    console.log(today);
    this.service.GetUserBookings(this.uid).subscribe((data: any) => {
      console.log(data);
      this.bookingDetails = data.map((obj: { date?: any }) => {
        let Booking = {};
        if (new Date(obj.date) >= new Date(today)) {
          this.upcomigbookingsexists += 1;

          Booking = { ...obj, isupcoming: true };
        } else {
          this.pastbookingsexists += 1;
          Booking = { ...obj, isupcoming: false };
        }
        return Booking;
      });
      console.log(this.bookingDetails);
    });
  }

  deletebooking(bid: any) {
    this.service.cancelUserBookings(bid).subscribe((data) => {
      console.log(data), this.getuserBookings();
    });
  }

  openDialog(bid: any, review: string, rating: number): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '250px',
      data: { review: review, rating: rating, bid: bid },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }
  edituserdetails(): void {
    console.log(this.editUserDetailsForm.value);
    if (this.editUserDetailsForm.valid) {
      this.service
        .updateUser(this.uid, this.editUserDetailsForm.value)
        .subscribe((data) => {
          console.log(data);
          this.getUserDetails()
        });
    }
  }
}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'feedback_dailog.html',
})
export class DialogOverviewExampleDialog {
  rating: number = 3;
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    private service: UserService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {
    console.log(this.data);
    this.rating = data.rating;
    console.log(this.data.rating);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  SubmitFeedback(review: string, rating: number): void {
    const obj = {
      review: review,
      rating: rating,
    };
    console.log(obj);
    this.service.updateFeedback(this.data.bid, obj).subscribe((data) => {
      console.log(data);
      location.reload();
      // this.dialogRef.close();
    });
  }
  ratechange(rating: number) {
    this.rating = rating;
    console.log(rating);
  }
}
