<?php
header('Content-Type: application/json;charset=utf-8');
require('connection.php');
//Define your table name here in which you want to retrieve data to convert into JSON.

$room_name = $_POST["room_name"];
$room_password = $_POST["room_password"];

$sql = "SELECT id FROM rooms WHERE room_name = \"$room_name\" and password = \"$room_password\" ";

$result = mysqli_query($conn ,$sql);


$arrays = array();
	while ($row = mysqli_fetch_assoc($result)) {
		
		$arrays[] = $row;

	}

	
	if(count($arrays) != 0)
	echo json_encode(array("server_response"=>$arrays));
else
	echo json_encode(array("server_response"=>false));

    mysqli_free_result($result);
$conn->close();
?>