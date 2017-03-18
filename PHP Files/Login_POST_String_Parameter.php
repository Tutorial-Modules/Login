<?php
define("DB_HOST", "localhost");   			//Host Name(By default it is localhost)
define("DB_USER", "");					//User name
define("DB_PASSWORD", "");				//User password
define("DB_NAME", "Module");				//Database Name

$email=$_POST['email'];
$password=$_POST['password'];

//$email='a@g.com';
//$password='12345';

$connect=mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);	
$query = "SELECT * FROM Login where email='$email' AND password='$password' ";
$result = mysqli_query($connect, $query);	
$number_of_rows = mysqli_num_rows($result);
	
if($number_of_rows > 0) {
	echo 'Success';
}
else {
	echo 'Failed';
}

?>
