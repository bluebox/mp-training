
// when javaScript runs this code, this callback function xyz() forms a closure with count
function attachEventListner(){
    let count = 0;
    document.getElementById("ClickMe").addEventListener("click", function xyz(){
        console.log("Long long ago, in a galaxy far away......", ++count);
    })
}

attachEventListner();