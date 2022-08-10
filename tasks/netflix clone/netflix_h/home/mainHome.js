let items = document.querySelector('.items')

let next = document.querySelector('.next')
let prev = document.querySelector('.prev')

console.log(items.offsetTop)
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

let pos = [0, 0, 0, 0, 0]

let item = document.querySelectorAll(".item");


const prevFun = (i) => {
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


const nextFun = (i) => {
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