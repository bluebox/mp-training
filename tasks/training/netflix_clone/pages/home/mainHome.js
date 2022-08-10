import { videos } from '../../data.js'
console.log(videos)

let item = document.querySelectorAll('.item')

function prevFun (i) {
  console.log(i)
  pos[i] -= item[i].offsetWidth
  if (pos[i] < 0) {
    pos[i] = 0
  }
  item[i].scroll({
    left: pos[i],
    behavior: 'smooth'
  })
}

function nextFun (i) {
  console.log(i)
  pos[i] += item[i].offsetWidth
  if (pos[i] > 17 * 265) {
    pos[i] = 17 * 265 - item[i].offsetWidth
  }
  item[i].scroll({
    left: pos[i],
    behavior: 'smooth'
  })
}

let nextBtns = document.querySelectorAll('.next')
let prevBtns = document.querySelectorAll('.prev')

let pos = []
for (let i = 0; i < item.length; i++) {
  pos.push(0)
  nextBtns[i + 1].addEventListener('click', () => nextFun(i))
  prevBtns[i + 1].addEventListener('click', () => prevFun(i))
}

const displayItem = video => {
  document.querySelector(
    '.body'
  ).style.backgroundImage = `linear-gradient(to right, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${video.image})`
  document.querySelector('.heading').textContent = video.name
  document.querySelector('.description').textContent = video.description
  // document.querySelector(".play_btn").textContent=video.description
  console.log(video)
  // localStorage.removeItem("video")
  localStorage.setItem('video', JSON.stringify(video))
  window.location.href = '../moviepage/itempage.html'
}

let items = document.querySelector('.items')
for (let video of videos) {
  let item = document.createElement('div')
  item.classList.add('item')
  let image = document.createElement('img')
  image.classList.add('large_image')
  image.src = `${video.image[0]}`
  let name = document.createElement('div')
  name.classList.add('content')
  name.textContent = video.name
  item.appendChild(image)
  item.appendChild(name)
  item.addEventListener('click', () => displayItem(video))
  items.appendChild(item)
}

let next = document.querySelector('.next')
let prev = document.querySelector('.prev')

let position = 0

next.addEventListener('click', () => {
  console.log('object')
  position += items.offsetWidth
  if (position > 17 * 195) {
    position = 17 * 195 - items.offsetWidth
  }
  items.scroll({
    // top: 378,
    left: position,
    behavior: 'smooth'
  })
})

prev.addEventListener('click', () => {
  console.log('hari')
  position -= items.offsetWidth
  if (position < 0) {
    position = 0
  }
  items.scroll({
    left: position,
    behavior: 'smooth'
  })
})

// let image = document.querySelector(".large_image");
// console.log(image.computedStyleMap().get("transform"));
