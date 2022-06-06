$.get("http://localhost:8080/academic",function(data,status){
    document.getElementById("academicTest").innerHTML=data;
});
//http://localhost:8080/oauth/token?grant_type=password&username=admin&scope=all&password=222&client_id=clientId&client_secret=111
// $.post("http://localhost:8080/oauth/token?grant_type=password&username=admin&scope=all&password=222&client_id=clientId&client_secret=111",function(data,status){
//     $.get("http://localhost:8080/privatePageName?access_token="+data.access_token,function(data1,status1){
//         document.getElementById("private").innerHTML=data1;
//     });
// });

$.post("http://localhost:8080/oauth/token?grant_type=password&username=zhangsan&scope=all&password=123&client_id=clientId&client_secret=111",function(data,status){
    $.get("http://localhost:8080/privatePageName?access_token="+data.access_token,function(data1,status1){
        document.getElementById("private").innerHTML=data1;
    });
});