function myfunc() {
    console.log("clicked")
    dropdown = document.querySelector(".dropdown");
    if (dropdown.status == "off") {
        dropdown.style.display = "block";
        dropdown.status = "on";
        console.log(1);
    }
    else {
        dropdown.style.display = "none";
        dropdown.status = "off";
        console.log(0);
    }
}

function problemVote() {
    problem_id = document.querySelector("#problem_id").innerHTML
    console.log(problem_id)
    const geturl = "http://127.0.0.1:8000/api/problem-detail/" + problem_id + "/"
    console.log(geturl)
    const posturl = "http://127.0.0.1:8000/api/problem-vote/" + problem_id + "/"
    req = new XMLHttpRequest()
    reqq = new XMLHttpRequest()
    req.open("GET", geturl)
    req.send()
    req.onreadystatechange = (e) => {
        res = req.responseText
        response = res
        console.log(response['likes'])
        response['likes'] = response['likes'] + 1
        reqq.open('POST', posturl, true)
        reqq.setRequestHeader('Content-type', 'application/json; charset=UTF-8')
        reqq.send(response);
    }
}