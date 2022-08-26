var present = 1;

function prev() {
    const node = document.querySelector(".advlogo");
    if (present == 1) {
        present = 4
    }
    present -= 1;
    node.src = "images/mobiles/" + present + ".png";
}

function next() {
    const node = document.querySelector(".advlogo");
    if (present == 3) {
        present = 0
    }
    present += 1;
    node.src = "images/mobiles/" + present + ".png";
}

currcateg = -1;

const arr = ["mobiles", "electronics", "footwear", "foot wear", "apparel"]

function search() {
    const node = document.querySelector(".categ");
    const input = document.querySelector("input").value;
    var value = input.toString();
    value = value.toLowerCase();
    console.log(value);
    if (arr.includes(value)) {
        window.location.href= "http://127.0.0.1:5500/" + value + ".html";
    }
    else {
        alert("enter a proper category");
    }
}