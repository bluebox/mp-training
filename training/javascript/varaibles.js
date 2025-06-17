    const btn = document.querySelector("button");
    const text = document.querySelector("p");

    btn.addEventListener("click",updateBtn);
    function updateBtn(){
        if(text.textContent === "Machine Started")
        {
            text.textContent = "Machine Stopped";
            btn.textContent = "Start";
        }else
        {
            text.textContent = "Machine Started";
            btn.textContent = "Stop";
        }
    }