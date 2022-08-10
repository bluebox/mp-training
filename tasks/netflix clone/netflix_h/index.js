let faq_ans = [
  "Netflix is a streaming service that offers a wide variety of award-winning TV shows, movies, anime, documentaries and more on thousands of internet-connected devices.</br> You can watch as much as you want, whenever you want, without a single ad  all for one low monthly price. There's always something new to discover, and new TV shows and movies are added every week!",
  'Watch Netflix on your smartphone, tablet, Smart TV, laptop, or streaming device, all for one fixed monthly fee. Plans range from ₹ 199 to ₹ 799 a month. No extra costs, no contracts.',
  "Watch anywhere, anytime, on an unlimited number of devices. Sign in with your Netflix account to watch instantly on the web at netflix.com from your personal computer or on any internet-connected device that offers the Netflix app, including smart TVs, smartphones, tablets, streaming media players and game consoles.You can also download your favourite shows with the iOS, Android, or Windows 10 app. Use downloads to watch while you're on the go and without an internet connection. Take Netflix with you anywhere.",
  'Netflix is flexible. There are no annoying contracts and no bindings. You can easily cancel your account online in two clicks. There are no cancellation fees  start or close your account at any time.',
  "Netflix's huge library includes feature films, documentaries, TV shows, anime, award-winning Netflix Originals and more. Watch as much as you want, anytime.",
  "Your membership includes the Netflix Kids Experience. Kids enjoy family TV shows and movies their own way, but you can control the content they watch. Children's profiles have PIN-protected parental controls that allow you to block children from viewing content with maturity ratings and block titles you don't want children to see."
]
const jserver = 'http://localhost:2906/users'

function faq (i) {
  for (let i = 0; i < 6; i++) {
    document.querySelector(`.ans${i + 1}`).textContent = ''
    document.querySelector(`.ans${i + 1}`).style.padding = '0%'
    document.querySelector(`.ques${i + 1}`).style.display = 'block'
    document.querySelector(`.ques1${i + 1}`).style.display = 'none'
  }
  console.log(document.querySelector(`.ans${i + 1}`))
  document.querySelector(`.ans${i + 1}`).textContent = faq_ans[i]
  document.querySelector(`.ans${i + 1}`).style.padding = '3%'
  document.querySelector(`.ques${i + 1}`).style.display = 'none'
  document.querySelector(`.ques1${i + 1}`).style.display = 'block'
}
function faq1 (i) {
  console.log(document.querySelector(`.ans${i + 1}`))
  document.querySelector(`.ans${i + 1}`).textContent = ''
  document.querySelector(`.ans${i + 1}`).style.padding = '0%'
  document.querySelector(`.ques${i + 1}`).style.display = 'block'
  document.querySelector(`.ques1${i + 1}`).style.display = 'none'
}

function  email_verify()
{
 let email = document.querySelector('.input').value
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
  {
    fetch(`${jserver}/?email=${email}`)
    .then(res => res.json())
    .then(data => {
      if (data.length == 0) {
        localStorage.setItem("email",email)
        window.location.href = './signup/signup.html'
      } else  {
        window.location.href = './signin/signin.html'
      }
    })
    
  }
  else{
    document.querySelector('.span').innerHTML="enter valid email";
  }
    
}


