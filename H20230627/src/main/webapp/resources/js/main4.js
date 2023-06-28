let jsonData = `[{"id":1,"first_name":"Kamilah","last_name":"Demaine","email":"kdemaine0@patch.com","gender":"Female","salary":1746},
{"id":2,"first_name":"Harry","last_name":"Rentoll","email":"hrentoll1@multiply.com","gender":"Male","salary":4405},
{"id":3,"first_name":"Innis","last_name":"Lunney","email":"ilunney2@dot.gov","gender":"Male","salary":1521},
{"id":4,"first_name":"Merrili","last_name":"Hill","email":"mhill3@free.fr","gender":"Female","salary":4816},
{"id":5,"first_name":"Maggy","last_name":"Konzelmann","email":"mkonzelmann4@oracle.com","gender":"Female","salary":3821},
{"id":6,"first_name":"Myrvyn","last_name":"Ranson","email":"mranson5@dedecms.com","gender":"Male","salary":3512},
{"id":7,"first_name":"Farrell","last_name":"McCuffie","email":"fmccuffie6@ox.ac.uk","gender":"Agender","salary":1407},
{"id":8,"first_name":"Jilli","last_name":"Balaam","email":"jbalaam7@thetimes.co.uk","gender":"Female","salary":1802},
{"id":9,"first_name":"Hayley","last_name":"Fintoph","email":"hfintoph8@theglobeandmail.com","gender":"Agender","salary":3973},
{"id":10,"first_name":"Uta","last_name":"Aysh","email":"uaysh9@bloglovin.com","gender":"Female","salary":2608},
{"id":11,"first_name":"Ermengarde","last_name":"Hebden","email":"ehebdena@stanford.edu","gender":"Female","salary":1659},
{"id":12,"first_name":"Filmore","last_name":"Swin","email":"fswinb@unicef.org","gender":"Bigender","salary":3365},
{"id":13,"first_name":"Kalli","last_name":"Sculley","email":"ksculleyc@weather.com","gender":"Female","salary":2819},
{"id":14,"first_name":"Adora","last_name":"Knight","email":"aknightd@wikipedia.org","gender":"Female","salary":3952},
{"id":15,"first_name":"Aylmer","last_name":"Mahood","email":"amahoode@elegantthemes.com","gender":"Male","salary":4169}]`;

let data = JSON.parse(jsonData);
/*console.log(data);*/

let xhtp = new XMLHttpRequest();
xhtp.open('get', 'memberList.do');
xhtp.send();
xhtp.onreadystatechange = function (){
	if(xhtp.readyState == 4 && xhtp.status == 200){
		console.log(xhtp.responseText);
		/*let data = JSON.parse(xhtp.responseText);
		console.log(data);*/
	}
}