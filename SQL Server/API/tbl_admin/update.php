<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Method: POST");
header("Access-Control-Max-Age:3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';
include_once '../objects/tbl_admin.php';

$database = new Database();
$db = $database->getConnection();

$admin = new Admin($db);

$data =
json_decode (file_get_contents("php://input"));
{
	$admin->id_admin = $data->id_admin;
  
	$admin->name = $data->name;
	$admin->username = $data->username;
	$admin->password = $data->password;

	if ($admin -> update()) {
		http_response_code(201);
		echo json_encode(array("Message" => "Admin was Updated" ));
	}
	else{
		http_response_code(503);
		echo json_encode(array("Message" => "Unable to update admin" ));
	}
}

?>