<?

class databases{

	var $lastError = "",
		$mysql;

	function connect(){
		$this->mysql = new mysqli("localhost", "root", "", "wow");
	}

	//Arguments: $query -> SQL-Query
	//			 $paramTypes -> array("s","s","i") datatypes from parameters (string, string, integer)
	//           $params -> array($username, $password) parameters
	function selectQuery($query, $paramTypes, $params){
			
		if (is_null($this->mysqlWeb))
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