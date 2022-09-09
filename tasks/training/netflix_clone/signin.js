const verify = () => {
    const email = document.getElementById("email").value;
    const passwd = document.getElementById("passwd").value;
    alert(email + passwd);
    if (email.length == 0 || passwd.length < 6) {
        alert("fill valid details")
    } else {
        window.open("index.html");
    }

}
const check = (ele) => {
    
    var e = document.getElementById('email').value;

    if (e.length ==0 ) {
        alert('please enter email id first ');
    }
    // if(ele.value.length<8)
    //     alert("password must be above 8 chars");
}