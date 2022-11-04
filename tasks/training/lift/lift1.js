var cur_dir = "";
var u_floors = [];
var d_floors = [];
var lift_cur_floor = 0;

function changeFloor(f_val, dir = cur_dir) {
  if (dir == "" || (u_floors.length == 0 && d_floors.length == 0)) {
    if (f_val > lift_cur_floor) {
      u_floors.push(f_val);
      u_floors.sort();
      cur_dir = "U";
    } else if (f_val < lift_cur_floor) {
      d_floors.push(f_val);
      d_floors.sort();
      d_floors.reverse();
      cur_dir = "D";
    }
  } else if (f_val > lift_cur_floor && cur_dir == "U" && dir == cur_dir) {
    u_floors.push(f_val);
    u_floors.sort();
  } else if (f_val > lift_cur_floor && cur_dir == "U" && dir !== cur_dir) {
    d_floors.push(f_val);
    d_floors.sort();
    d_floors.reverse();
  }
  // } else if (f_val > lift_cur_floor && cur_dir == "U" && dir == cur_dir) {
  //   u_floors.push(f_val);
  //   u_floors.sort();
  //   // d_floors.reverse();
  else if (f_val < lift_cur_floor && cur_dir == "U" && dir == "D") {
    d_floors.push(f_val);
    d_floors.sort();
    d_floors.reverse();
  } else if (
    f_val < lift_cur_floor &&
    (cur_dir == "D" || cur_dir == "") &&
    dir == "U"
  ) {
    u_floors.push(f_val);
    u_floors.sort();
    // d_floors.reverse();
  } else if (
    f_val < lift_cur_floor &&
    (cur_dir == "D" || cur_dir == "") &&
    dir == "D"
  ) {
    d_floors.push(f_val);
    d_floors.sort();
    d_floors.reverse();
  }
  // ---------------------
  else if (f_val < lift_cur_floor && cur_dir == "U") {
    d_floors.push(f_val);
    d_floors.sort();
    d_floors.reverse();
  } else if (f_val > lift_cur_floor && cur_dir == "D" && dir == cur_dir) {
    u_floors.push(f_val);
    u_floors.sort();
    // d_floors.reverse();
  } else if (f_val > lift_cur_floor && cur_dir == "D" && dir !== cur_dir) {
    u_floors.push(f_val);
    u_floors.sort();
  }
  //  else if (f_val > lift_cur_floor && cur_dir == "D" && dir !== cur_dir) {
  //   u_floors.push(f_val);
  //   u_floors.sort();
  // }
  else if (f_val < lift_cur_floor && cur_dir == "D" && dir == "D") {
    d_floors.push(f_val);
    d_floors.sort();
    d_floors.reverse();
  } else if (f_val < lift_cur_floor && cur_dir == "D" && dir == "U") {
    u_floors.push(f_val);
    u_floors.sort();
    // d_floors.reverse();
  }
  console.log("u", u_floors, "d", d_floors);
  console.log(cur_dir);
}

// let all_floors = document.querySelectorAll(".floor");
// var all_floors_val = [];
// for (floor of all_floors) {
//   // Math.floor(floor.getBoundingClientRect().y);
//   all_floors_val.push(floor.getBoundingClientRect().y);
// }

setInterval(() => {
  if (cur_dir == "U" && u_floors.length > 0) {
    // handleFloor[0];
    lift_cur_floor = u_floors.shift();
    handleFloor(lift_cur_floor);
    if (u_floors.length == 0) {
      cur_dir = "D";
    }
  } else if (
    (cur_dir == "D" && d_floors.length == 0) ||
    (cur_dir == "U" && u_floors.length == 0)
  ) {
    // handleFloor[0];
    cur_dir = "";
  } else if (cur_dir == "D" && d_floors.length > 0) {
    // handleFloor[0];
    lift_cur_floor = d_floors.shift();
    handleFloor(lift_cur_floor);
    if (d_floors.length == 0) {
      cur_dir = "U";
    }
  }
  console.log("ui", u_floors, "di", d_floors);
  console.log("dir", cur_dir);
}, 4000);

function handleFloor(floor) {
  let floor_elem = document.getElementById("floor" + floor);
  let floor_pos = floor_elem.getBoundingClientRect().y;
  let lift = document.getElementById("lift");
  let lift_pos = lift.getBoundingClientRect().y;
  console.log(Math.floor(lift_pos), Math.floor(floor_pos));
  lift.style.top = floor_pos + "px"; // assigning lift position
}
