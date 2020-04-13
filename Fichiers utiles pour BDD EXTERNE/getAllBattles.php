<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 

//Initialisation des variables pour la bdd
$user_name = "root";
$password = "";
$database = "battleDB";
$server = "localhost";

//Connexion à la bdd
//$dbh=mysqli_connect($server, $user_name, $password, $database);
 
 //Connexion dans la BDD
 $db_handle = mysqli_connect('localhost', 'root', '');
 $db_found = mysqli_select_db($db_handle, $database);

 if($db_found){
     //echo "db trouve";

    // get all products from products table
    $result = mysqli_query($db_handle, "SELECT * FROM battles");
    
    // check for empty result
    if (mysqli_num_rows($result) > 0) {
        //echo "rows ok";
        // looping through all results
        // products node
        $response["battles"] = array();
    
        while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
            // temp user array
            $battle = array();
            $battle["id"] = $row["id"];
            $battle["team1"] = $row["team1"];
            $battle["team2"] = $row["team2"];
            $battle["total1"] = $row["total1"];
            $battle["total2"] = $row["total2"];
            $battle["latitude"] = $row["latitude"];
            $battle["longitude"] = $row["longitude"];
    
            // push single product into final response array
            array_push($response["battles"], $battle);
        }
        // success
        $response["success"] = 1;
    
        // echoing JSON response
        echo json_encode($response);

        mysqli_close($db_handle);
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No products found";
    
        // echo no users JSON
        echo json_encode($response);
    }

}
else
    {
       // echo "Sorry, Database not found";
    }
?>