  let scale = 1;

  function inc1() {
    
    scale += 0.1; 
    const imge = document.getElementById("g1");
    imge.style.transform = `scale(${scale})`;
    imge.style.transformOrigin = "center center center center"; 
  }