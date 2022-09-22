 let stars = document.getElementById("stars");
 let moon = document.getElementById("moon");
 let  mountain = document.getElementById("mountain");
 let text = document.getElementById("text");
 let  road = document.getElementById("road");
 let  bike = document.getElementById("bike");
 let  broom = document.getElementById("broom");
 let projects =document.getElementById("projects");
 let section1 = document.getElementById("fold1");
 let navbar = document.getElementById("nav");
 let fold3 = document.getElementById("fold3");
 let broommir = document.getElementById("broommir");
 let projectsmir = document.getElementById("projectsmir");
 let box = document.getElementById("box");
 let box2 = document.getElementById("box2");
 let box3 = document.getElementById("box3");
 let box4 = document.getElementById("box4");
 let study = document.getElementById("study");
 window.addEventListener('scroll',function(){
     var value = window.scrollY;
     stars.style.top = value * 0.5 + 'px';
     moon.style.left = -value * 0.5 + 'px';
     moon.style.top = value*0.80 + 'px';
     mountain.style.top = value * 0.30 + 'px';
     
     text.style.left = -(value * 2.30) + 'px';
    //  text.style.fontSize = (value / 2) + 'px';
     bike.style.left = -(value * 2.30) + 'px';
     section1.style.top = (value) + 'px';
     if (value >500){
    broom.style.left = (value * 0.30)+120 + 'px';
    projects.style.left = (value * 0.30)+100 + 'px';

     }
     if (value>630){
        navbar.style.top= (value*10) +'px';  
        section1.style.top = -(value*0.3) + 'px';
     }
    else{
        navbar.style.top= "-17rem" 
     }
     if (value>580){

        road.style.top =(value * 0.30 )-100 + 'px';
     }
     else{
        road.style.top = value * 0.15 + 'px';
     }
    if (value>1065){
      broommir.style.left= -(value*0.30)+700+ 'px';
      projectsmir.style.left= -(value*0.30)+80+ 'px';
    }
    if (value>1350){
         if (box.style.top < "-324px"){
      box.style.top=-(value*0.25)+'px';
   }
   
   
      if (box2.style.top < "-304px"){
   box2.style.top=-(value*0.20)+'px';
}

   if (box3.style.top < "-225.25px"){
box3.style.top=-(value*0.15)+'px';
}

   if (box4.style.top < "-224px"){
box4.style.top=-(value*0.20)+'px';
}
      
    }
 })

