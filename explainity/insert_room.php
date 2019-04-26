<?php
header('Content-Type: application/json;charset=utf-8');
require('connection.php');
//Define your table name here in which you want to retrieve data to convert into JSON.

$room_name = $_POST["room_name"];
$password = $_POST["password"];

$sql = "insert into explainity.rooms(room_name,password) VALUES(\"$room_name\",\"$password\")";

if(mysqli_query($conn ,$sql))
  	echo json_encode(array("server_response"=>true));
  	else
  	  	echo json_encode(array("server_response"=>false));




$conn->close();
?>