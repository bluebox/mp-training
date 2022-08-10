const usersUrl = 'http://localhost:3000/users'

let accordian = document.getElementsByClassName('FAQ__title')

for (let i = 0; i < accordian.length; i++) {
  accordian[i].addEventListener('click', function () {
    if (this.childNodes[1].classList.contains('fa-plus')) {
      this.childNodes[1].classList.remove('fa-plus')
      this.childNodes[1].classList.add('fa-times')
    } else {
      this.childNodes[1].classList.remove('fa-times')
      this.childNodes[1].classList.add('fa-plus')
    }

    let content = this.nextElementSibling
    if (content.style.maxHeight) {
      content.style.maxHeight = null
    } else {
      content.style.maxHeight = content.scrollHeight + 'px'
    }
  })
}

// let arr = []
// const usersDataFetch = () => {
//   fetch('http://localhost:3000/users')
//     .then(res => res.json())
//     .then(data => {
//       console.log(data)
//       arr = [...data]
//     })
//     .catch(err => console.log(err))
// }
// usersDataFetch()
let newUser
const handleSignUp = () => {
  fetch(usersUrl)
    .then(res => res.json())
    .then(data => {
      newUser = data.filter(
        user => user.email == document.querySelector('.email__input').value
      )
      // console.log(newUser)
      if (newUser.length == 0) {
        let email = document.querySelector('.email__input').value
        localStorage.setItem('email', email)
        document.getElementById('getStarted').href =
          './pages/signup/signup.html'
      } else {
        // alert(`${newUser[0].email} already exists! Please sign in`)
        document.getElementById('error_message').style.display = 'block'

        document.getElementById('getStarted').href = './pages/signin/signin.html'
      }
    })
    .catch(err => console.log(err))
}

const hideError = () => {
  document.getElementById('error_message').style.display = 'none'
}
