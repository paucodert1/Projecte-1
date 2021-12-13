<?php

    include('php/conex.php');
    
    $web= "";
    $order= "P.CODI";

    if(isset($_POST["buscar"])){

        $categories=$_POST["cat"];
        $material=$_POST["mat"];
        if(empty($_POST["cat"]) and empty($_POST["mat"])){

            $web= "";

        }elseif(empty($_POST["cat"])){

            $web= "where CODI_MATERIAL='".$material."'";

        }elseif(empty($_POST["mat"])){

            $web= "where C.NOM ='".$categories."'";

        }else{

            $web= "where C.NOM ='".$categories."' and CODI_MATERIAL='".$material."'";

        }

        if(empty($_POST["ord"])){

            $order= "P.CODI";

        }else{

            $order= $_POST["ord"];

        }

    }




    $sql = "SELECT distinct P.CODI, P.NOM_PRODUCTE, C.NOM nom_categoria, P.Marca, P.STOCK, P.PREU, P.CODI_PORTA, F.CODI_MATERIAL
    FROM producte P 
    JOIN organitzat O on O.CODI_PRODUCTE=P.CODI
    JOIN fets_amb F  on P.CODI=F.CODI_PRODUCTE
    JOIN categories C  on O.CODI_CATEGORIES=C.CODI $web order by $order;";
    $result = $connexio->query($sql);

    require 'view/inventari.view.php';
    
?>