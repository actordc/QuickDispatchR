<?php
	/*
	This file belongs in the webserver-directory on the server, where the mysql database is running on.
	*/
	$mysqli = mysqli_connect("localhost", "berndvsql5", "DispatchR", "berndvsql5");
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	$username = $_GET["user"];
	$password = $_GET["pass"];

	function checkPassword($username, $password){
		$sql = sprintf("SELECT user.username, user.password FROM user WHERE username = %s LIMIT 1", $username);
		echo "1";
		$result = $mysqli->query($sql);
		echo mysqli_error($mysqli);
		$row = $result->fetch_assoc();
		echo "3";
		echo $row["username"];
		echo "4";
	}

	echo "testi\n";
	checkPassword();

?>