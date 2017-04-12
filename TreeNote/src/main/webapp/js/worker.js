/* [ ---- Gebo Admin Panel - icons ---- ] */

//메세지 수신하여 시간이 오래 걸리는 작업을 처리한다
onmessage = function(event) {
  var receiveData = event.data;
  sleep(3000); 
     
  //워커를 호출한 곳으로 결과 메시지를 전송한다
  var sendData = receiveData + "OK~ I'm Worker"
  postMessage(sendData)
}

function sleep(milliSeconds) {  
  var startTime = new Date().getTime(); // get the current time   
  while (new Date().getTime() < startTime + milliSeconds); // hog cpu 
} 


