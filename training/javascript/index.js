// function additem() {
//     const input = document.getElementById('name');
//     const textarea = document.getElementById('tex');
//     if (input.value.trim()!='') {
//         if(confirm("Enter ok")){
//         textarea.value += input.value.trim() + '\n';
//         input.value = '';
//         }
//         input.value="";
//         let name=prompt("Enter name");
//         const pp=document.getElementByTagName("p");
//         pp.innerHtml=name;
//     }
//     else
//         alert("It shouldn't be empty");
// }
function additem() {
    const input = document.getElementById('name');
    const textarea = document.getElementById('tex');

    if (input.value.trim() !== '') {
        if (confirm("Enter ok")) {
            textarea.value += input.value.trim() + '\n';
            input.value = '';
        }

        let name = prompt("Enter name");
        if (name) {
            const paragraph = document.getElementsByTagName("p")[0];
            if (paragraph) {
                paragraph.innerHTML = name;
            } else {
                alert("No <p> element found.");
            }
        }
    } else {
        alert("It shouldn't be empty");
    }
}


// document.getElementById("add").addEventListener("click",function(){
//     const input = document.getElementById('name');
//     const textarea = document.getElementById('tex');
//     if (input.value.trim()!=="") {
//         textarea.value += input.value.trim() + '\n';
//         input.value = '';
//     }
//     else
//         alert("It shouldn't be empty");
// });
// document.getElementById("add").addEventListener("click", function () {
//     const input = document.getElementById('name');
//     const textarea = document.getElementById('tex');
    
//     if (input.value.trim() !== '') {
//         textarea.value += input.value.trim() + '\n';
//         input.value = '';
//     } else {
//         alert("It shouldn't be empty");
//     }
// });
