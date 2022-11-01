import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  cancelledBookingsexists: number = 0;
  upcomigbookings: any = [];
  pastbookings: any = [];
  cancelledbookings: any = [];
  upcomigbookingsloadmore: any = [];
  pastbookingsloadmore: any = [];
  cancelledbookingsloadmore: any = [];
  bookingstoDisaplay: number = 5;

  editUserDetailsForm: FormGroup = new FormGroup({
    user_name: new FormControl('', [Validators.required]),
    user_email: new FormControl('', [Validators.required, Validators.email]),
  });
  constructor(
    private service: UserService,
    private arouter: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router
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
    this.cancelledBookingsexists = 0;
    this.upcomigbookings = [];
    this.pastbookings = [];
    this.cancelledbookings = [];
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
      this.bookingDetails = data.map(
        (obj: { date?: any; isCancelled?: any }) => {
          let Booking = {};
          if (new Date(obj.date) >= new Date(today)) {
            if (!obj.isCancelled) {
              this.upcomigbookingsexists += 1;
              this.upcomigbookings.push(obj);
            } else {
              this.cancelledBookingsexists += 1;
              this.cancelledbookings.push(obj);
            }
          } else {
            if (!obj.isCancelled) {
              this.pastbookingsexists += 1;
              this.pastbookings.push(obj);
            } else {
              this.cancelledBookingsexists += 1;
              this.cancelledbookings.push(obj);
            }
          }
        }
      );
      this.loadMoreDisplay(0);

      console.log(
        this.upcomigbookings,
        this.pastbookings,
        this.cancelledbookings
      );
    });
  }

  loadMoreDisplay(x: any): void {
    this.bookingstoDisaplay += x;
    this.upcomigbookingsloadmore = this.upcomigbookings.slice(
      0,
      this.bookingstoDisaplay
    );
    this.pastbookingsloadmore = this.pastbookings.slice(
      0,
      this.bookingstoDisaplay
    );
    this.cancelledbookingsloadmore = this.cancelledbookings.slice(
      0,
      this.bookingstoDisaplay
    );
  }

  openDialog(e: Event, bid: any, review: string, rating: number): void {
    e.stopPropagation();
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '250px',
      data: { review: review, rating: rating, bid: bid },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.getuserBookings();
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
          this.getUserDetails();
        });
    }
  }
  openDialog2(
    e: Event,
    enterAnimationDuration: string,
    exitAnimationDuration: string,
    bid: any
  ): void {
    e.stopPropagation();
    const dialogRef=this.dialog.open(DialogAnimationsExampleDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
      data: { bid: bid },
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.getuserBookings();
      console.log('The dialog was closed');
    });
  }
  loadmore(): void {
    this.loadMoreDisplay(5);
  }
  bookingspage(bid: any): void {
    this.router.navigate(['user/bookings', bid]);
  }
  div1: boolean = true;
  div2: boolean = false;
  div3: boolean = false;

  div1Function() {
    this.bookingstoDisaplay = 5;
    this.loadMoreDisplay(0);
    this.div1 = true;
    this.div2 = false;
    this.div3 = false;
  }

  div2Function() {
    this.bookingstoDisaplay = 5;
    this.loadMoreDisplay(0);
    this.div2 = true;
    this.div1 = false;
    this.div3 = false;
  }

  div3Function() {
    this.bookingstoDisaplay = 5;
    this.loadMoreDisplay(0);
    this.div3 = true;
    this.div2 = false;
    this.div1 = false;
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
      // location.reload();
      this.dialogRef.close();
    });
  }
  ratechange(rating: number) {
    this.rating = rating;
    console.log(rating);
  }
}
@Component({
  selector: 'dialog-animations-example-dialog',
  templateUrl: 'cancel_confirmation.html',
})
export class DialogAnimationsExampleDialog {
  constructor(
    public dialogRef: MatDialogRef<DialogAnimationsExampleDialog>,
    private service: UserService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {
    console.log(data);
  }
  deletebooking(e: Event) {
    // e.stopPropagation();
    this.service.cancelUserBookings(this.data.bid).subscribe((data) => {
      console.log(data);
      // this.getuserBookings();
      this.dialogRef.close();
    });
  }
}
