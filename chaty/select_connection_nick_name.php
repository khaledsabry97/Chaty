<?php
header('Content-Type: application/json;charset=utf-8');
require('connection.php');
//Define your table name here in which you want to retrieve data to convert into JSON.


$room_id = $_POST["room_id"];
$nick_name = $_POST["nick_name"];

$sql = "SELECT * FROM connections where room_id = $room_id and nick_name = \"$nick_name\"";


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

 //This script is designed by Android-Examples.com

?>