import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  username: null | string = localStorage.getItem('username')

  profile!:any;
  problems!: any;
  easyCent: any;
  hardCent: any;
  mediumCent: any;
  followers: any;
  following: any;
  isFollowing: Boolean = false;
  followingToggle: Boolean = false;
  followersToggle: boolean = false;
  user: any;
  profileSubscription!: Subscription;
  problemSubscription!: Subscription;
  id = localStorage.getItem('username');
  followingEachOther: Boolean = false
  
  
  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, private service: RegisterService) { 
    this.user = this.route.snapshot.paramMap.get('id')
    // this.user = this.route.snapshot.paramMap.get('id')
    // this.service.getProfile(this.user).subscribe((data: any) => {
    //   console.log('====================================');
    //   console.log(data);
    //   this.profile = data['profile']
    //   this.following = data['following']
    //   this.followers = data['followers']
    //   this.service.profile = data['profile'];
    //   console.log('====================================');
    // })
    // this.problems = this.service.getStats(this.user).subscribe((data: any) => {
    //   this.problems = data;
    //   this.easyCent = (data['easy'] / data['totalEasy']) * 100;
    //   this.mediumCent = (data['medium'] / data['totalMedium']) * 100;
    //   this.hardCent = (data['hard'] / data['totalHard']) * 100;
    // })
  }



  showOrHideIng() {
    this.followingToggle = !this.followingToggle
    console.log(this.followingToggle);
    
  }

  showOrHideErs() {
    this.followersToggle = !this.followersToggle
  }

  openProfile(user: any) {
    console.log(user.user.username);
    this.router.navigateByUrl(`profile/${user.user.username}`).then( () => {
      location.reload()
    });
    this.router.navigate(['profile/', user.user.username])
    this.service.gotoProfile(user).subscribe((data: any) => {
      console.log(data);
    })
  }

  ngOnInit(): void {

    this.id = localStorage.getItem('username');


    this.profileSubscription = this.service.getProfile(this.user).subscribe((data: any) => {
      console.log('====================================');
      console.log(data);
      this.profile = data['profile']
      this.following = data['following']
      this.followers = data['followers']
      this.service.profile = data['profile'];
      for (let user of this.followers) {
        if (user.user.username === this.id) {
          this.isFollowing = true;
        }
      }
      for (let user of this.following) {
        if (user.user.username === this.id && this.isFollowing) {
          this.followingEachOther = true
          break
        } 
      }
      console.log('====================================');
    })
    this.problemSubscription = this.service.getStats(this.user).subscribe((data: any) => {
      this.problems = data;
      this.easyCent = (data['easy'] / data['totalEasy']) * 100;
      this.mediumCent = (data['medium'] / data['totalMedium']) * 100;
      this.hardCent = (data['hard'] / data['totalHard']) * 100;
    })

  }

  unfollowUserAgain(user: any, id: any) {
    console.log("/////////");
    
    this.service.unfollowUser(id, user.user.id).subscribe((data) => {
      console.log(data);
      location.reload()
    })
  }

  unfollowUser(user: any, i: any) {
    this.service.unfollowUser(user.user.id, this.profile.user.id).subscribe((data) => {
      console.log(data);
      this.following.splice(i, i + 1)
    })
  }

  followUser(user: any, id: any) {
    this.service.followUser(id, user.user.id).subscribe((data) => {
      console.log(data);
      location.reload()
    })
  }

  // checkBothFOllowing(id: any, profile: any) {
  //   this.service.checkBothFOllowing(id, profile.user.id).subscribe((data: any) => {
  //     console.log(data);
  //   })
  //   return true
  // }

  ngOnDestroy(): void {
    this.problemSubscription.unsubscribe();
    this.profileSubscription.unsubscribe();
  }

}
