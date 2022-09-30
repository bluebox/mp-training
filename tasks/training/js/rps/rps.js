
const totalscore={computerscore:0 ,playerscore:0}
function getcomputerchoice()
{
    let rpsChoices = ['Rock', 'Paper', 'Scissors']
    let computerchoice=rpsChoices[Math.floor(Math.random()*3)]

    return computerchoice
}

function getresult(playerchoice ,computerchoice)
{
    let score;
    if(playerchoice === computerchoice)
    score=0
    else if(playerchoice === 'Rock' &&  computerchoice === 'Scissors')
    score=1
    else if(playerchoice === 'Paper' && computerchoice === "Rock")
    score=1
    else if(playerchoice === 'Scissors' && computerchoice === 'Paper')
    score=1
    else
    score=-1

    return score

}
function showresult(score,playerchoice,computerchoice)
{
    let result = document.getElementById('result')
    switch(score)
    {
        case -1:
            result.innerText = "You Lose!"
            break;
          case 0:
            result.innerText = "It's a Draw!"
            break;
          case 1:
            result.innerText = "You Win!"
            break;
    }

    let playerscore=document.getElementById("player-score")
    let hands =document.getElementById("hands")
    playerscore.innerText = totalscore['playerscore']
      
    hands.innerText =  `dgjj +playerchoice+ dhdjjj computerchoice+`
}





function onclickrps(playerchoice)
{
    const computerchoice =getcomputerchoice()
    const score =getresult(playerchoice.value, computerchoice)
    totalscore['playerscore']+=score
    showresult(score,playerchoice.value, computerchoice)
}


function playgame()
{
  let rpsbuttons=document.querySelectorAll(".rpsButton")

  rpsbuttons.forEach(rpsbutton => {
    rpsbutton.onclick =() =>onclickrps(rpsbutton)
  })

  let endgamebutton= document.getElementById('endGameButton')
   endgamebutton.onclick =() => endgame(totalscore)

}
function endgame(totalscore)
{
    totalscore['playerscore']=0
    totalscore['computerscore']=0
    let playerscore=document.getElementById("player-score")
    let hands =document.getElementById('hands')
    let result =document.getElementById("result")

    playerscore.innerText= ''
    hands.innerText= ''
    result.innerText=''
    
}

playgame()