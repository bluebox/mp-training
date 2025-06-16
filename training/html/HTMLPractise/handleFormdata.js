
const formele= document.getElementById("login-wrapper");
formele.addEventListener("submit",function(e) {
    e.preventDefault();
const formdata= new FormData(this);

for( const [key,value] of formdata.entries())

    {
        console.log(`${key} : ${value}`);
       
    }
this.reset();
window.location.href="homepage.html"

})