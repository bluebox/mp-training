let form=document.querySelector("form");
form.addEventListener("submit",function(event){
event.preventDefault();
// alert("form submitted");
let inp=document.querySelector("input");
console.dir(inp);
console.log(inp.innerText);
console.log(inp.value);
alert(`hello ${inp.value} your account is created`)
})