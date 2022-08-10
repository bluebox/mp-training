
const jserver = 'http://localhost:2906/users'

document.querySelector('.email_input').value = localStorage.getItem('email')

function signup () {
  console.log('object')
  let password = document.querySelector('.password_input').value

  
  fetch(jserver, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({
      email: localStorage.getItem('email'),
      password: password
    })
  })
  window.location.href="../home/mainHome.html";
}
