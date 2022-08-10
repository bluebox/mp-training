var prev_elem = ""
var decs_elements = document.querySelectorAll(".faq_description");
console.log(decs_elements);
var img_elements = document.querySelectorAll(".addImage")
function showDescription(title_id, img_id) {
    let desc_elem = document.getElementById(title_id + "d");
    let img_elem = document.getElementById(img_id)
    var new_desc = []
    var new_imgs =[]
    for (let index = 0; index < decs_elements.length; index++) {
        if ( decs_elements[index].id !== (title_id + "d") ) {
            new_desc.push(decs_elements[index])
        } 
    }
    for (let index = 0; index < img_elements.length; index++) {
        if ( img_elements[index].id !== (img_elem.id) ) {
            new_imgs.push(img_elements[index])
        } 
    }
    console.log(new_desc);


    if(desc_elem.style.display === "block"){
        console.log("block");
        desc_elem.style.display = "none";
        img_elem.src = "./images/add.png";
        hide_desc(new_desc)
        change_img(new_imgs)

    }
    else{
        desc_elem.style.display = "block";
        img_elem.src = "./images/cancel.png";
        console.log("none");    
        hide_desc(new_desc)
        change_img(new_imgs)

        
        // console.log(desc_elem.style.display);
    }


}

function hide_desc(elems) {
    elems.forEach(item => {
        item.style.display = "none"
    })
}
function change_img(elems){
    elems.forEach(item=>{
        item.src = "./images/add.png";
    })
}

// ----------------------

let position = 0
function handleScroll(id,direction){
    let items = document.getElementById(`${id}`)
    if (direction=="right") {
        position += items.offsetWidth
        console.log(position);
    if (position > 12 * 250) {
      position = 12 * 250 - items.offsetWidth
    }
    items.scroll({
      // top: 378,
      left: position,
      behavior: 'smooth'
    })
    }
    else{
        position -= items.offsetWidth
  if (position < 0) {
    position = 0
  }
  items.scroll({
    left: position,
    behavior: 'smooth'
  })
    }
    

}
// let prev = document.querySelector('.left')
// let items = document.querySelector(".row_posters")
// let position = 0

// next.addEventListener('click', () => {
//   console.log('object')
//   position += items.offsetWidth
//   if (position > 17 * 195) {
//     position = 17 * 195 - items.offsetWidth
//   }
//   items.scroll({
//     // top: 378,
//     left: position,
//     behavior: 'smooth'
//   })
// })

// prev.addEventListener('click', () => {
//   console.log('hari')
//   position -= items.offsetWidth
//   if (position < 0) {
//     position = 0
//   }
//   items.scroll({
//     left: position,
//     behavior: 'smooth'
//   })
// })