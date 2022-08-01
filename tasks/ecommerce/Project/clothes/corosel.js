let slideIndex = 1
export const main = (items) => {
  let container = document.createElement('div')
  container.classList.add('container')
  // let filter_section = document.createElement('div')
  // filter_section.classList.add('filter_section')
  let products = document.createElement('div')
  products.classList.add('products')
  for (let ele of items) {
    let product = document.createElement('div')
    product.classList.add('product')
    product.innerHTML = `<img src=${ele.variant[0].image[0]} alt="" class="product_image">
                          <div class="product_content" style="text-align: center;">
                          
                          <h2 class="product_brand">${ele.brand}</h2>
                          <p style="">${ele.variant.length} colours available</p>
                          <p class="product_price">₹ ${ele.variant[0].price}</p>
                          <div style="color: green;">
                              Free Delivery
                          </div>
                          </div>`
    product.addEventListener('click', () => displayProduct(ele))
    products.appendChild(product)
  }
  // container.appendChild(filter_section)
  container.appendChild(products)
  document.querySelector('body').appendChild(container)
}

function displayProduct(ele) {
  console.log(ele)
  document.querySelector('.container').style.display = 'none'

  let item = document.createElement('div')
  item.classList.add('item')

  // creating select element
  let select = document.createElement('select')
  select.classList.add('color')

  for (let e of ele.variant) {
    let option = document.createElement('option')
    option.value = e.color
    option.innerHTML = e.color
    select.appendChild(option)
  }

  let itemImage = document.createElement('div')
  itemImage.classList.add('item_image_box')

  let slideContainer = document.createElement('div')
  slideContainer.classList.add('slideshow-container')

  let dots = document.createElement('div')
  dots.classList.add('dots')

  create_slides_and_dots(slideContainer, dots, ele.variant[0].image)

  itemImage.appendChild(slideContainer)
  itemImage.appendChild(dots)

  item.appendChild(itemImage)

  let sizeSelect = document.createElement('select')
  sizeSelect.classList.add('color')
  sizeSelect.innerHTML = `<option value="S">S</option>
                                  <option value="M">M</option>
                                  <option value="L">L</option>`

  select.addEventListener('change', variantChange)

  //creating price element
  let price = document.createElement('p')
  price.classList.add('price')
  price.textContent = '₹ ' + ele.variant[0].price

  //creating div and appending price and select elements
  let variants = document.createElement('div')
  variants.classList.add('variants')
  variants.appendChild(select)
  variants.appendChild(sizeSelect)
  variants.appendChild(price)

  //creating brand, description and buttons
  //brand
  let brand = document.createElement('h1')
  brand.classList.add('brand')
  brand.textContent = ele.brand
  //description
  let description = document.createElement('p')
  description.classList.add('description')
  description.textContent = ele.description
  //buttons
  let addCartBtn = document.createElement('button')
  addCartBtn.classList.add('addcart', 'button')
  addCartBtn.textContent = 'add to cart'

  let buyNowBtn = document.createElement('button')
  buyNowBtn.classList.add('buynow', 'button')
  buyNowBtn.textContent = 'buy now'
  let buttons = document.createElement('div')
  buttons.classList.add('buttons')
  buttons.appendChild(addCartBtn)
  buttons.appendChild(buyNowBtn)

  //item content box
  let itemContent = document.createElement('div')
  itemContent.classList.add('item_content')
  itemContent.appendChild(brand)
  itemContent.appendChild(description)
  itemContent.appendChild(variants)
  itemContent.appendChild(buttons)

  // itemImage.innerHTML = `<img class="item_image kids_formal_black" src=${ele.variant[0].image[0]} alt="">`

  item.appendChild(itemContent)
  document.querySelector('.items').appendChild(item)

  function variantChange() {
    let index = ele.variant.findIndex((obj) => obj.color == select.value)
    // itemImage.innerHTML = `<img class="item_image kids_formal_black" src=${ele.variant[index].image} alt="">`
    price.textContent = '₹ ' + ele.variant[index].price

    //removing children in slideContainer
    var child = slideContainer.lastElementChild
    while (child) {
      slideContainer.removeChild(child)
      child = slideContainer.lastElementChild
    }

    //removing children in dots
    var child = dots.lastElementChild
    while (child) {
      dots.removeChild(child)
      child = dots.lastElementChild
    }

    create_slides_and_dots(slideContainer, dots, ele.variant[index].image)

    itemImage.appendChild(slideContainer)
    itemImage.appendChild(dots)

    slideIndex = 1
    showSlides(slideIndex, slideContainer, dots)

    function currentSlide(n) {
      showSlides((slideIndex = n), slideContainer, dots)
    }
  }

  function create_slides_and_dots(slideContainer, dots, imageArr) {
    for (let img of imageArr) {
      let imagesLength = imageArr.length

      //creating images corosel
      let myslide = document.createElement('div')
      myslide.classList.add('mySlides', 'fade')

      myslide.innerHTML = `<div class="numbertext"> ${
        imageArr.indexOf(img) + 1
      } / ${imagesLength}</div>
                            <img src=${img} style="width:100%;">
                            `
      slideContainer.appendChild(myslide)

      let dot = document.createElement('div')
      dot.classList.add('dot')
      dot.innerHTML = `<img src=${img} style="width:4rem; height:4rem;">`
      dot.addEventListener('click', () =>
        currentSlide(imageArr.indexOf(img) + 1),
      )
      dots.appendChild(dot)
    }
  }

  slideIndex = 1
  showSlides(slideIndex, slideContainer, dots)

  function currentSlide(n) {
    showSlides((slideIndex = n), slideContainer, dots)
  }

  // item.addEventListener("click", closeProduct)
  let removeBtn = document.createElement('button')
  removeBtn.classList.add("remove_button")
  removeBtn.innerHTML = '<img src="https://cdn3.iconfinder.com/data/icons/simple-web-navigation/165/cross-512.png" alt="" style="width: 2rem; color:red;">'

  removeBtn.addEventListener("click", closeProduct);
  item.appendChild(removeBtn)
  document.querySelector('body').appendChild(item)
}

function closeProduct() {
  document.querySelector('.container').style.display = 'grid';
  let body = document.querySelector('.item')
  body.remove();
}

function showSlides(n, slideContainer, dots) {
  let i
  let slides = slideContainer.children
  let dotss = dots.children
  if (n > slides.length) {
    slideIndex = 1
  }
  if (n < 1) {
    slideIndex = slides.length
  }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = 'none'
  }
  for (i = 0; i < dotss.length; i++) {
    dotss[i].className = dotss[i].className.replace(' active', '')
  }
  slides[slideIndex - 1].style.display = 'block'
  dotss[slideIndex - 1].className += ' active'
}
