const usersUrl = 'http://localhost:3000/users'

const handleSignin = e => {
  // e.preventDefault();
  console.log(e)
}
let arr

// console.log(arr)
console.log(document.querySelector('.homePage'))

// var form = document.querySelector('form')
function handleForm () {
  // event.preventDefault()
  console.log('object')
  let email = document.querySelector('.signin_email').value
  let password = document.querySelector('.signin_password').value
  console.log('email', email)
  console.log('password', password)

  fetch(`${usersUrl}/?email=${email}&&password=${password}`)
    .then(res => res.json())
    .then(data => {
      console.log(data)
      if (data.length != 0) {
        alert("login successful");
        window.location.href="../home/mainHome.html"
        document.querySelector('.homepage_url').href = ''

      } else {
        // document.querySelector('.error_message').textContent =
        //   'Ivalid Login Details'
        alert('Incorrect Login Details')
      }
    })
    .catch(err => console.log(err))
}
// form.addEventListener('submit', handleForm)
