
var s={
    name : "Bhanu",
    class : "BTech"
}
s.name="Srinu";
console.log(s);
localStorage.setItem(s.name,s);

function fun(){
    const x=document.querySelector('input[name="branch"]:checked');
    if(x){
        console.log(x.value);
    }
    else{
        console.log("branch not found");
    }
}
function fun1(){
    let x=document.querySelectorAll('input[name="lang"]:checked');
    let l=[];
    x.forEach(element => {
        l.push(element.value);
    });
    console.log(l);
}

