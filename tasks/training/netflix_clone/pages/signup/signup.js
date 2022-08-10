const email = localStorage.getItem('email')

document.querySelector('.email_input').value = email
let password = ''

document.querySelector('.next_step_button').addEventListener('click', e => {
//   console.log(e)
//   e.preventDefault()

    let password = document.querySelector('.password_input').value

    console.log(password)
    if (password && password != ' ') {
      let user = { email: email, password: password }
      console.log(user)
      fetch('http://localhost:3000/users', {
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(user)
      })
      window.location.href="../home/mainHome.html"
      // e.preventDefault();
    } else {
      console.log('invalid password')
    }
})

function handleSignUp (e) {
  //   e.preventDefault();
  console.log(e)
  e.preventDefault()

  let password = document.querySelector('.password_input').value
  console.log(password)
  if (password && password != ' ') {
    let user = { email: email, password: password }
    console.log(user)
    fetch('http://localhost:3000/users', {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(user)
    })

    // e.preventDefault();
  } else {
    console.log('invalid password')
  }
}
