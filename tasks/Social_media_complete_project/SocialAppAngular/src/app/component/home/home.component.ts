import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  combineLatest,
  map,
  Observable,
  of,
  startWith,
  Subscription,
  switchMap,
  tap,
} from 'rxjs';
import { AppConstants } from 'src/app/common/app-constants';
import { ChatMessage } from 'src/app/model/chat-message';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { ChatMessageService } from 'src/app/service/chat-message.service';
import { UserService } from 'src/app/service/user.service';
import { SnakebarComponent } from '../snakebar/snakebar.component';

import { Element, NONE_TYPE } from '@angular/compiler';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  @ViewChild('endOfChat')
  endOfChat!: ElementRef;
  userData: User = JSON.parse(this.authService.getAuthUserFromCache())
  selectedUserData!: User
  users_data: User[] = []
  chatMessageList: ChatMessage[] = []
  url: string = 'ws://127.0.0.1:8000/ws/as/' + JSON.stringify(this.userData.id) + '/'

  private subscriptions: Subscription[] = [];
  ws = new WebSocket(this.url)
  chatInputMessage!: FormGroup
  searchControl = new FormControl('');
  messageControl = new FormControl('');
  chatListControl = new FormControl('');

  messages$: Observable<any[]> | undefined;


  constructor(private userService: UserService,
    private authService: AuthService,
    private fb: FormBuilder,
    private chatService: ChatMessageService,
    private matSnackbar: MatSnackBar,

  ) { }

  ngOnInit(): void {


    this.chatInputMessage = this.fb.group({
      selectedUserData: new FormControl('', Validators.required)
    })
    this.ws.onopen = function (event) {
      console.log("websocket is opened...", event)
    }

    this.ws.onerror = function (event) {
      console.log("websocket is receiving error...", event)
    }
    this.ws.onclose = function (event) {
      console.log("websocket is closed...", event)
    }
    this.userData = JSON.parse(this.authService.getAuthUserFromCache())
    console.log("hello world")
    console.log(this.userData)
    console.log(this.userData.id)


    this.authService.getUserFollowersData(this.userData.id).subscribe((data: User[]) => {
      data.forEach(x => {
        console.log(x)
        this.users_data.push(x)
      }
      )
      console.log(this.users_data)
    })
  }
  wordWrap(str: string, maxWidth: number): any {
    var newLineStr = "\n";
    const done = false;
    let res = '';
    while (str.length > maxWidth) {
      let found = false;
      // Inserts new line at first whitespace of the line
      for (let i = maxWidth - 1; i >= 0; i--) {
        if (this.testWhite(str.charAt(i))) {
          res = res + [str.slice(0, i), newLineStr].join('');
          str = str.slice(i + 1);
          found = true;
          break;
        }
      }
      // Inserts new line at maxWidth position, the word is too long to wrap
      if (!found) {
        res += [str.slice(0, maxWidth), newLineStr].join('');
        str = str.slice(maxWidth);
      }

    }

    return res + str;
  }

  testWhite(x: any) {
    var white = new RegExp(/^\s$/);
    return white.test(x.charAt(0));
  };
  flag_check = false;
  createChat(user: User) {
    if (this.flag_check) {
      let a = document.getElementById("message-content") as any
      let ele = document.getElementById('message-content1') as any
      console.log(a.innerHTML)
      a.innerHTML = ""
      ele.innerHTML = ""

    }

    // this.selectedUserData = {}
    this.chatMessageList = []

    this.selectedUserData = user
    this.chatService.getChatData(user.id).subscribe({
      next: (data: ChatMessage[]) => {
        console.log(data)
        this.chatMessageList = []
        this.chatMessageList = data;
        this.flag_check = true
      },
      error: (errorResponse: HttpErrorResponse) => {
        this.matSnackbar.openFromComponent(SnakebarComponent, {
          data: AppConstants.snackbarErrorContent,
          panelClass: ['bg-danger'],
          duration: 5000
        });

      }
    })

  }

  sendMessage(selectedUserData: User) {
    let str = this.wordWrap(this.chatInputMessage.value.selectedUserData, 10);
    let send_to;
    console.log(selectedUserData.id, "user id is")
    console.log(this.userData.id, "user id is")
   
    let data = {
      "message": str,
      "sent_by": this.userData.id,
      "send_to": selectedUserData.id
    }
    let data1 = JSON.stringify(data)
    console.log(data1)
    this.ws.send(data1)

    function countWords(message: string) {
      const arr = message.split(' ');

      return arr

    }

    this.ws.onmessage = async function (event) {
      console.log("websocket is receiving message from server...", event)
      console.log("websocket is receiving message from server actual data is...", event.data)
      let ele = document.getElementById('message-content')
      let data = JSON.parse(event.data)
      let message = data.message
      let sent_by = data.sent_by
      console.log(message)
      console.log(sent_by, "sent_by")

      let arr = countWords(message)
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].length > 10) {
          arr[i] = arr[i].slice(0, arr[i].length / 2) + " " + arr[i].slice(arr[i].length / 2);
        }
      }
      let output = ''
      for (let i of arr) {
        output += i + " "
      }
      let str1: string = event.data
      // this.userData = JSON.parse(this.authService.getAuthUserFromCache()) 
      let user: any = localStorage.getItem('authUser')
      console.log(JSON.parse(user).id)
      let current_user_id = JSON.parse(user).id

      if (current_user_id == sent_by) {
        if (ele != null) {
          let timestampNow1 = Date.now()
          let date = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: true }).toLowerCase();
          const element = document.createElement("div")
          element.className = "message-sender";
          // element.style.width=100%;
          // element.style.display="flex"
          // const element1 = document.createElement("div")
          // element1.className = "message-content";

          // const node = document.createTextNode(output);
          // element1.appendChild(node)

          // element.appendChild(element1)

          // ele
          ele.innerHTML += "<div class=\"sender\" style=\"width:100%;padding: 0px 10px;border-radius: 10px; background-color: grey;display:flex;flex-direction:row-reverse\"><div style=\"\">" + output + "<div style=\"color:white;left: 0%;font-size: x-small;\">" + "..." + date + "</div> </div></div>"
          let ele1: any = document.getElementById('chat-area')

          let pixels = ele1.clientHeight;

          ele.scrollBy(0, pixels);


        }
      }
      else {
        let ele1 = document.getElementById('message-content1')
        if (ele1 != null) {
          let timestampNow1 = Date.now()
          let date = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: true }).toLowerCase();
          ele1.innerHTML += "<div style=\"width:100%;padding: 0px 10px;border-radius: 10px; background-color: rgb(128, 201, 201);display:flex;flex-direction:row\"><div style=\"\">" + output + "<div style=\"color:white;left: 0%;font-size: x-small;\">" + "..." + date + "</div> </div></div>"
          let pixels = ele1.clientHeight;

          ele1.scrollBy(0, pixels);


        }
      }


    }
    this.chatInputMessage.reset()




  }



}
