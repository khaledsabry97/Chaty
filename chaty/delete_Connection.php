<?php
header('Content-Type: application/json;charset=utf-8');
require('connection.php');
$ip = $_POST["ip"];

$sql = "DELETE from connections where ip = \"$ip\"";

if(mysqli_query($conn ,$sql))
	  	echo json_encode(array("server_response"=>true));
  	else
  	  	echo json_encode(array("server_response"=>false));

?>



