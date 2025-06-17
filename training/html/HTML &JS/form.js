var x=document.getElementById("count");
var y=document.getElementById("fillData");
y.innerHTML="";
rtypes=["2-Sharing","3-Sharing","4-Sharing","2-Sharing-AC","3-Sharing-AC","4-Sharing-AC"];
y.append(x.value);
function addDetails(){
    for(let i=1;i<=x.value;i++){
        h=document.createElement("h1");
        h.textContent="Guest "+i;
        y.appendChild(h);
        nlabel=document.createElement("label");
        nlabel.textContent="Name :             ";
        y.appendChild(nlabel);
        ninp=document.createElement("input");
        ninp.type="text";
        ninp.placeholder="Enter the name of Guest "+i;
        y.appendChild(ninp);
        br1=document.createElement("br");
        y.appendChild(br1);
        rlabel=document.createElement("label");
        rlabel.textContent="Type of room you want : ";
        y.appendChild(rlabel);
        rtype=document.createElement("select");
        rtype.name="Type of room you want : ";
        rtype.id="RoomType";
        rtypes.forEach(element => {
            opt=document.createElement("option");
            opt.value=element;
            opt.textContent=element;
            rtype.appendChild(opt);
        });
        y.appendChild(rtype);
        br2=document.createElement("br");
        y.appendChild(br2);
        fromDate=document.createElement("label");
        fromDate.textContent="Date of Joining :";
        y.appendChild(fromDate);
        finp=document.createElement("input");
        finp.type="date";
        y.appendChild(finp);
        br1=document.createElement("br");
        y.appendChild(br1);
        toDate=document.createElement("label");
        toDate.textContent="Date of Departure : ";
        y.appendChild(toDate);
        fout=document.createElement("input");
        fout.type="date";
        y.appendChild(fout);
        br1=document.createElement("br");
        y.appendChild(br1);
        proof=document.createElement("label");
        proof.textContent="What type of proof do you have: ";
        y.appendChild(proof);
        br1=document.createElement("br");
        y.appendChild(br1);
        opt1=document.createElement("input");
        opt1.type="radio";
        opt1.name="proof";
        y.appendChild(opt1);
        l=document.createElement("label");
        l.textContent="Adhar";
        y.appendChild(l);
        opt1=document.createElement("input");
        opt1.type="radio";
        opt1.name="proof";
        y.appendChild(opt1);
        l=document.createElement("label");
        l.textContent="Pan";
        y.appendChild(l);
        opt1=document.createElement("input");
        opt1.type="radio";
        opt1.name="proof";
        opt1.value="others";
        y.appendChild(opt1);
        l=document.createElement("label");
        l.textContent="Others";
        y.appendChild(l);
        br1=document.createElement("br");
        y.appendChild(br1);
        l=document.createElement("label");
        l.textContent="Please provide the image of the ID proof: ";
        y.appendChild(l);
        img=document.createElement("input");
        img.type="file";
        img.accept="image*/";
        y.appendChild(img);
        b=document.createElement("button");
        b.textContent="Display Image"
        let image=document.createElement("img");
        image.style.width="200px";
        image.style.height="200px";
        y.appendChild(image);
        img.addEventListener("change",function()
        {
            if(img.files && img.files[0]){
                const reader=new FileReader();
                reader.onload=function(e){
                    image.src=e.target.result;
                };
                reader.readAsDataURL(img.files[0]);
            }
        });
    }
    submit=document.createElement("input");
        submit.type="submit";
        submit.value="Submit your data";
        y.appendChild(submit);
}