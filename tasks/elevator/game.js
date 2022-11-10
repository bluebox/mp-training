// var que = [0, 0, 0, 0, 0];
// const lift = document.querySelector(".lift");
// var curr = 1;

// console.log("up");

// function move(floor) {
//   que[floor - 1] = 1;
//   console.log(que);
// }

// console.log("down");

// function goDown() {
//   for (let i = curr - 1; i < 5; i ++) {
//     if (que[i] == 1) {
//       lift.style.marginTop = (i) * 100 + "px";
//       lift.style.transition = "all " + Math.abs(lift.style.marginTop - i * 100) + "s " + "linear";
//       curr = i + 1;
//       console.log(curr);
//       que[i] = 0;
//     }
//   }
//   console.log(que);
//   goUp();
// }

// function goUp() {
//   for (let i = curr - 1; i > -1; i --) {
//     if (que[i] == 1) {
//       lift.style.top = (i) * 100 + "px";
//       curr = i + 1;
//       console.log(curr);
//     }
//   }
// }

// function main() {
//   goDown();
//   goUp();
// }

// setInterval(main, 1);

// var que = [0, 0, 0, 0, 0];
// const lift = document.querySelector(".lift");
// var curr = 1;

// console.log("up");

// function move(floor) {
//   que[floor - 1] = 1;
//   console.log(que);
// }

// console.log("down");

// function goDown() {
//   for (let i = curr - 1; i < 5; i ++) {
//     if (que[i] == 1) {
//       lift.style.marginTop = (i) * 100 + "px";
//       curr = i + 1;
//       console.log(curr);
//       clearInterval(id);
//       var id = setInterval(main, 1);
//       que[i] = 0;
//     }
//   }
//   console.log(que);
//   // goUp();
// }

// function goUp() {
//   for (let i = curr - 1; i > -1; i --) {
//     if (que[i] == 1) {
//       lift.style.top = (i) * 100 + "px";
//       curr = i + 1;
//       console.log(curr);
//     }
//   }
// }

// function main() {
//   goDown();
//   goUp();
// }

// var id = setInterval(main, 1);

var que = [0, 0, 0, 0, 0];
const lift = document.querySelector(".lift");
var curr = 1;
var direc = "d";

console.log("up");

function move(floor) {
  que[floor - 1] = 1;
  console.log(que);
}

console.log("down");

function goDown(i, direc) {
  if (que[i] == 1) {
    if (i == que.length - 1 && direc == "d") {
      direc = "u";
      que[i] = 0;
      lift.style.marginTop = (i) * 100 + "px";
      lift.innerHTML = i + 1;
      setTimeout(goDown, 4000, i - 1, direc);
    }
    else if (i == 0 && direc == "u") {
      direc = "d";
      que[i] = 0;
      lift.style.marginTop = (i) * 100 + "px";
      lift.innerHTML = i + 1;
      setTimeout(goDown, 4000, i + 1, direc);
    }
    else if (direc == "u") {
      que[i] = 0;
      lift.style.marginTop = (i) * 100 + "px";
      lift.innerHTML = i + 1;
      setTimeout(goDown, 4000, i - 1, direc);
    }
    else if (direc == "d") {
      que[i] = 0;
      lift.style.marginTop = (i) * 100 + "px";
      lift.innerHTML = i + 1;
      setTimeout(goDown, 4000, i + 1, direc);
    }
  }
  else {
    if (i == que.length - 1 && direc == "d") {
      direc = "u";
      goDown(i - 1, direc);
    }
    else if (i == 0 && direc == "u") {
      direc = "d";
      goDown(i + 1, direc);
    }
    else if (direc == "u") {
      goDown(i - 1, direc);
    }
    else if (direc == "d") {
      goDown(i + 1, direc);
    }
  }
}

setTimeout(goDown, 3000, 0, direc);