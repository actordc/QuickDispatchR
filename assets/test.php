<?php
error_reporting(E_ALL);
ini_set("display_errors", 1);

$user = $_GET["u"];
$pass = $_GET["p"];

class databases{

	var $lastError = "",
		$mysql;

	function connect(){
		$this->mysql = new mysqli("***", "***", '***', "***");
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
	echo "entered";
	$res = $mysql->selectQuery($sql, array("s"), array($user));
	$row = $res->fetch_assoc();
	$password_hash = $row["password"];
	if (crypt($password_entered, $password_hash) == $password_hash) {
	   echo "OK";
	}
}

checkPass($user, $pass, $mysql);

?>

