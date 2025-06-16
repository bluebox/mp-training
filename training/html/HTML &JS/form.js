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
        nlabel.textContent="Name :   ";
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
        fromDate.textContent="Date of Joining : ";
        y.appendChild(fromDate);
        finp=document.createElement("input");
        finp.type="date";
        y.appendChild(finp);
        br1=document.createElement("br");
        y.appendChild(br1);
        toDate=document.createElement("label");
        toDate.textContent="Date of Joining : ";
        y.appendChild(toDate);
        finp=document.createElement("input");
        finp.type="date";
        y.appendChild(finp);
        br1=document.createElement("br");
        y.appendChild(br1);

    }
    return y;
}