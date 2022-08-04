var num = 1;

function change() {
    const main = document.getElementsByClassName("btn");
    const red = document.querySelector(".btnr");
    const yellow = document.querySelector(".btny");
    const green = document.querySelector(".btng");
    if (num % 3 == 0) {
        red.setAttribute("style", "background-color:red");
        yellow.setAttribute("style", "background-color:yellow");
        green.setAttribute("style", "background-color:green");
        num += 1;
    }
    else if (num % 3 == 1) {
        red.setAttribute("style", "background-color:green");
        yellow.setAttribute("style", "background-color:red");
        green.setAttribute("style", "background-color:yellow");
        num += 1;
    }
    else {
        red.setAttribute("style", "background-color:yellow");
        yellow.setAttribute("style", "background-color:green");
        green.setAttribute("style", "background-color:red");
        num += 1;
    }
}