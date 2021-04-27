const waveAmount = 25
const waveRatio = 70/280
const minWidth = 80
const maxWidth = 300

document.onreadystatechange = () => {
  if (document.readyState === 'interactive') {
    let wavesContainer = document.getElementsByClassName("wavesContainer");
    let newWaves = generateWaveDivs()
    newWaves.forEach(function(wave){
      wavesContainer[0].appendChild(wave)
    })
  }
  // if(document.readyState == 'complete'){
  //   let startButton = document.getElementById("writeButton")
  //   startButton.onclick(move(document.getElementById("writeMessageModal"), "down", 500, 2000))
  // }
};

function buildWave(i, randomWidth, randomHeight, top) {
  let newWave = document.createElement("div")
  newWave.id = "wave" + i
  newWave.className = "wave"
  newWave.style =
      `width:${randomWidth}px; height: ${randomHeight}px; left:${Math.floor(
          Math.random() * 90)}%; top:${top}%; z-index:${top
      * 2}; position: absolute;`
  return newWave;
}

function generateWaveDivs() {
  let waveElements = []
  for (let i = 0; i < waveAmount; i++) {
    const randomWidth = Math.floor(Math.random() * maxWidth) + minWidth;
    const randomHeight = randomWidth * waveRatio;
    const top = Math.floor(Math.random() * 100);
    let newWave = buildWave(i, randomWidth, randomHeight, top);
    waveElements.push(newWave)
  }
  return waveElements
}


function move(element, direction, distance=20, duration=1000) {
  var topOrLeft = (direction==="left" || direction==="right") ? "left" : "top";
  var isNegated = (direction==="up" || direction==="left");
  if (isNegated) { distance *= -1; }
  var elStyle = window.getComputedStyle(element);
  var value = elStyle.getPropertyValue(topOrLeft).replace("px", "");
  var destination = Number(value) + distance;
  var frameDistance = distance / (duration / 10);
  function moveAFrame() {
    elStyle = window.getComputedStyle(element);
    value = elStyle.getPropertyValue(topOrLeft).replace("px", "");
    var newLocation = Number(value) + frameDistance;
    var beyondDestination = ( (!isNegated && newLocation>=destination) || (isNegated && newLocation<=destination) );
    if (beyondDestination) {
      element.style[topOrLeft] = destination + "px";
      clearInterval(movingFrames);
    }
    else {
      element.style[topOrLeft] = newLocation + "px";
    }
  }
  var movingFrames = setInterval(moveAFrame, 10);
}

function sendBottle(){
  const messageText = document.getElementById('writeMessageInput').value;
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "./api/v1/bottle/");
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  const message = {
    message : messageText
  }

  xhr.send(JSON.stringify(message));

  xhr.onload = function() {
    if (xhr.status != 200) { // analyze HTTP status of the response
      alert(`Error ${xhr.status}: ${xhr.statusText}`); // e.g. 404: Not Found
    } else { // show the result
      move(document.getElementById('writeMessageModal'), 'up', 800, 300)
    }
  };

  xhr.onprogress = function(event) {
    if (event.lengthComputable) {
      alert(`Received ${event.loaded} of ${event.total} bytes`);
    } else {
      alert(`Received ${event.loaded} bytes`); // no Content-Length
    }

  };

  xhr.onerror = function() {
    alert("Request failed");
  };
}

async function readMessage(){

  const fetchPromise = fetch("./api/v1/bottle/read");
  fetchPromise.then(response => {
    return response.json();
  }).then(bottle => {
    document.getElementById("readMessageInput").innerText = bottle.message;
    move(document.getElementById('readMessageModal'), 'down', 800, 300);
    // console.log(people);
  });

}