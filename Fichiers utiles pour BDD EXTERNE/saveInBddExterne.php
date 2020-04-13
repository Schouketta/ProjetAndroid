<?php
 
/*
 * Le code suivant permet de :
 * save une nouvelle battle dans la table "battles" de notre bdd EXTERNE.
 * Pour cela nous utilisons une requête INSERT INTO
 */
 
// array for JSON response
$response = array();
 

// check for required fields
if (isset($_POST['team1']) && isset($_POST['team2']) && 
    isset($_POST['tech1']) && isset($_POST['art1']) && isset($_POST['espace1']) 
    && isset($_POST['style1']) && isset($_POST['originalite1']) && isset($_POST['total1'])
    && isset($_POST['tech2']) && isset($_POST['art2']) && isset($_POST['espace2'])
    && isset($_POST['style2']) && isset($_POST['originalite2'])
    && isset($_POST['total2']) && isset($_POST['longitude']) && isset($_POST['latitude']) ) 
{
 
    $team1 = $_POST['team1'];
    $team2 = $_POST['team2'];
    $tech1 = $_POST['tech1'];
    $tech2 = $_POST['tech2'];
    $art1= $_POST['art1'];
    $art2= $_POST['art2'];
    $espace1= $_POST['espace1'];
    $espace2= $_POST['espace2'];
    $style1= $_POST['style1'];
    $style2= $_POST['style2'];
    $originalite1= $_POST['originalite1'];
    $originalite2= $_POST['originalite2'];
    $total1= $_POST['total1'];
    $total2= $_POST['total2'];
    $longitude= $_POST['longitude'];
    $latitude= $_POST['latitude'];

    //Initialisation des variables pour la bdd
    $user_name = "root";
    $password = "";
    $database = "battleDB";
    $server = "localhost";

    //Connexion dans la BDD
    $db_handle = mysqli_connect('localhost', 'root', '');
    $db_found = mysqli_select_db($db_handle, $database);
    

    if($db_found)
    {  
        // mysql update row with matched pid
        $result = mysqli_query($db_handle, "INSERT INTO battles(team1, team2, tech1, art1, espace1, style1, originalite1, total1, tech2, art2, espace2, style2, originalite2, total2, longitude, latitude) 
        values ('$team1', '$team2', '$tech1', '$art1', '$espace1', '$style1','$originalite1', '$total1', '$tech2', '$art2', '$espace2', '$style2', '$originalite2', '$total2', '$longitude', '$latitude')");
        /*$result = mysqli_query($db_handle, "INSERT INTO battles(team1, team2, tech1, art1, espace1, style1, originalite1, total1, tech2, art2, espace2, style2, originalite2, total2, longitude, latitude) 
        values ('hola', 'coucou', '1', '1', '1', '2','1', '6', '1', '1', '1', '1', '1', '5', '7.676', '3.7647')");
        */

        // check if row inserted or not
        if (mysqli_query($db_handle, $result)) 
        {     
            // successfully updated
            $response["success"] = 1;
            $response["message"] = "Nouvelle battle successfully add dans BDD EXTERNE";
    
            // echoing JSON response
            echo json_encode($response);
        } 
       
        mysqli_close($db_handle);

    }
} 
else 
{
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>