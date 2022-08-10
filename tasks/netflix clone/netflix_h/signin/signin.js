
let form = document.querySelector('form')

const jserver = 'http://localhost:2906/users'



function signin(e) {
  e.preventDefault()
  let password = document.querySelector('.signin_password').value
  let email = document.querySelector('.signin_email').value
  fetch(`${jserver}/?email=${email}&&password=${password}`)
    .then(res => res.json())
    .then(data => {
      if (data.length == 0) {
        document.querySelector('.errormsg').textContent = 'Invalid Credentials'
      } else {
        window.location.href = "../home/mainHome.html";
        
      }
    })

}
form.addEventListener('submit', signin)
