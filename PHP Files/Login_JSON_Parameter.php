<?php
define("DB_HOST", "localhost"); 			//Host Name(By default it is localhost)
define("DB_USER", ""); 					//User name
define("DB_PASSWORD", "");   				//User password
define("DB_NAME", "");  				//Database Name

$value = json_decode(file_get_contents('php://input'));
$email=$value->email;
$password=$value->password;

//$email='a@gm.com';
//$password='12345';

$connect=mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);	
$query = "SELECT * FROM Login where email='$email' AND password='$password' ";
$result = mysqli_query($connect, $query);	
$number_of_rows = mysqli_num_rows($result);

$temp_array  = array();	
if($number_of_rows > 0) {
	$temp_array='Success';
}
else
{
	$temp_array='Failed';
}
	
header('Content-Type: application/json');
echo json_encode(array("message"=>$temp_array));
mysqli_close($connect);

?>
