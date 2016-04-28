<?php
/*
* This script needs php 7 to run. The main problem in older versions is the function $stmt->get_result() Which should be supported in PHP >= 5.30, but, however, it isn't.
* If you know a fix for this problem, feel free to contribute at github.com/actordc/QuickDispatchR
*/



//Use only if the production server doesn't have error reporting enabled and you wan't to debug something.
//error_reporting(E_ALL);
//ini_set("display_errors", 1);

$user = $_GET["u"];
$pass = $_GET["p"];

class databases{

	var $lastError = "",
		$mysql;

	function connect(){
		$this->mysql = new mysqli("localhost", "danube", '!D3v!', "danube");
	}

	//Arguments: $query -> SQL-Query
	//			 $paramTypes -> array("s","s","i") datatypes from parameters (string, string, integer)
	//           $params -> array($username, $password) parameters
	function selectQuery($query, $paramTypes, $params){
			
		if (is_null($this->mysql))
			$this->connect();

		$stmt = $this->prepare($query, $paramTypes, $params);
		$stmt->execute();
		$res = $stmt->get_result();

        return $res;
	}

	function insertQuery($query, $paramTypes, $params){
		if(is_null($this->mysql))
			$this->connect();

		$stmt = $this->prepare($query, $paramTypes, $params);
		$stmt->execute();
	}

	function prepare($query, $paramTypes, $params){

		$stmt = $this->mysql->prepare($query);

		$a_params = array();

		$param_type = "";
		$n = count($paramTypes);
		for($i = 0; $i < $n; $i++){
			$param_type .= $paramTypes[$i];
		}		

		$a_params[] = & $param_type;

		for($i = 0; $i < $n; $i++){
			$a_params[] = & $params[$i];
		}

		call_user_func_array(array($stmt, "bind_param"), $a_params);
		return $stmt;
	}
}

$mysql = new databases();

function checkPass($user, $password_entered, $mysql) {
	$sql = sprintf("SELECT user.username, user.password FROM user WHERE user.username = ? LIMIT 1");
	$res = $mysql->selectQuery($sql, array("s"), array($user));
	$row = $res->fetch_assoc();
	$password_hash = $row["password"];
	//Normally, password_verify would be used here. But it isn't working yet. To keep the coding going, I will fix this problem later on.
	if ($password_entered == $password_hash) {
	   	echo "OK";
	} else if ($password_entered != $password_hash) {
		echo "NO";
	} else {
		echo "An error occured.";
	}
}

checkPass($user, $pass, $mysql);

?>

