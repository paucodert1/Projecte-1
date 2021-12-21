<?php

        $connexio = new mysqli('localhost', 'root', 'root', 'TECLATS');
    
        if ($connexio ->connect_errno) {
            die("Error al conectar a la base de dades");
        }else{


        }
    
?>