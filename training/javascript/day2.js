function setTime(){
    const sysTime=new Date();
    document.getElementById("time").innerText=sysTime.toString();//new Date().toUTCString();
}
document.getElementById("cc").addEventListener("click",function(){
    console.log(document.body.style.backgroundColor);
    let col="#";
    let str="0123456789ABCDEF";
    for(let i=0;i<6;i++){
        col+=str[Math.floor(Math.random()*16)];
    }
    document.body.style.backgroundColor=col;
});
function cg(){
    const gett=document.body.style.backgroundColor;
    console.log(gett);
    let col="#";
    let str="0123456789ABCDEF";
    for(let i=0;i<6;i++){
        col+=str[Math.floor(Math.random()*16)];
    }
    document.body.style.backgroundColor=col;
}
// const filee=document.getElementById("fil");
// filee.addEventListener("change",function(){
//     const ff=filee.files[0];
//     if(!ff.name.toLowerCase().endsWith(".jpg")){
//         filee.value="";
//         alert("Insert jpg Files only");
//     }
// })
// const allFiles=document.getElementById("fil");
// console.log(allFiles.length);
// allFiles.addEventListener("change",function(){
//     const all=allFiles.files;
//     for(let i=0;i<all.length;i++){
//         var ff=all[i];
//         let message=this.nextElementSibling;
//         if(!ff.name.toLowerCase().endsWith(".jpg")){
//                  message.hidden=false;
//                  message.textContent="Insert only Jpg FIle";
//                  message.style.color="red";
//         }
//         else{
//             message.hidden=true
//         }
            

//     }
// })
const fileInputs=document.querySelectorAll("#fil");
console.log(fileInputs.length);
console.log(fileInputs);
fileInputs.forEach(input=>{
    input.addEventListener("change",function(){
        let files=input.files;
        console.log(files.length);
        console.log(files);
        let message=input.nextElementSibling;
        for(let i=0;i<files.length;i++){
            const file=files[i];
            if(!file.name.toLowerCase().endsWith(".jpg")){
                message.hidden=false;
                message.style.color="red";
                message.textContent="Insert only jpg file";
            }
        }
    })
})




window.setInterval(setTime,1000);
window.onload=setTime;