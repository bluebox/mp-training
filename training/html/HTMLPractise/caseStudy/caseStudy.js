    //form input into objects
    let userData=[];
    let form=document.getElementById("userData");
    form.addEventListener("submit",function(e) {
        e.preventDefault();
    let formdata= new FormData(this);
       let formuser={};
    for(let[key,value] of formdata.entries())
    {
        formuser[key]=value;
    }
    userData.push(formuser);

    this.reset();


    })

//form data pushing into table

let tableBody= document.getElementById("data-entry");
for(let person of userData)
{
   
}
