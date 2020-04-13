<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (pid)
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
 

 if($db_found)
 {
    //echo "db trouve";
    // check for post data
    if (isset($_GET["id"])) 
    {
        $id = $_GET['id'];
    
        // get a product from products table
        $result = mysqli_query($db_handle, "SELECT * FROM battles WHERE id = $id");
    
        if (!empty($result)) {
            // check for empty result
            if (mysqli_num_rows($result) > 0) {
    
                //echo "get 1 detail battle OK";
                $result = mysqli_fetch_array($result, MYSQLI_ASSOC);
                $battle = array();
                $battle["id"] = $result["id"];
                $battle["team1"] = $result["team1"];
                $battle["team2"] = $result["team2"];
                $battle["total1"] = $result["total1"];
                $battle["total2"] = $result["total2"];
                $battle["latitude"] = $result["latitude"];
                $battle["longitude"] = $result["longitude"];

                // success
                $response["success"] = 1;
    
                // user node
                $response["battle"] = array();
    
                array_push($response["battle"], $battle);
    
                // echoing JSON response
                echo json_encode($response);
                mysqli_close($db_handle);
                
            } else {
                // no product found
                $response["success"] = 0;
                $response["message"] = "No product found";
    
                // echo no users JSON
                echo json_encode($response);
            }
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
    
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // required field is missing
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
    
        // echoing JSON response
        echo json_encode($response);
    }

}
else
    {
        //echo "Sorry, Database not found";
    }
?>
