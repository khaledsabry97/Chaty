<?php
header('Content-Type: application/json;charset=utf-8');
require('connection.php');
//Define your table name here in which you want to retrieve data to convert into JSON.

$room_id = $_POST["room_id"];
$nick_name = $_POST["nick_name"];
$ip = $_POST["ip"];
$last_time_entered = $_POST["last_time_entered"];

$sql = "UPDATE connections SET last_time_entered=$last_time_entered, ip = \"$ip\" WHERE nick_name = \"$nick_name\" and room_id = $room_id";

if(mysqli_query($conn ,$sql))
  	echo json_encode(array("server_response"=>true));
  	else
  	  	echo json_encode(array("server_response"=>false));




$conn->close();
?>