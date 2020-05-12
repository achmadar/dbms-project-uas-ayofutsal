<?php
// required headers
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// database connection will be here
// files needed to connect to database
include_once 'config/database.php';
include_once 'objects/tbl_user.php';

// get database connection
$database = new Database();
$db = $database->getConnection();

// instantiate product object
$user = new User($db);

// submitted data will be here
// get posted data
$data = json_decode(file_get_contents("php://input"));

// $data->username_admin = isset($_GET['username_admin']) ? $_GET['username_admin'] : die();
// $data->password_admin = isset($_GET['password_admin']) ? $_GET['password_admin'] : die();

// set product property values
$user->username = $data->username;
$user->password = $data->password;

$user->login();

if ($user->id_user != null) {
    // create array
    $listuser_arr = array();

    // set response code - 200 OK
    http_response_code(200);

    // make it json format
    array_push($listuser_arr, array('id_user' => $user->id_user));
    echo json_encode(array("result" => $listuser_arr));
} else {
    // set response code - 404 Not found
    http_response_code(404);

    // tell the user product does not exist
    echo json_encode(array("message" => "Login was failed."));
}
