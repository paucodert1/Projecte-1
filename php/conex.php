<?php

        $connexio = new mysqli('localhost', 'root', 'Fat/3232', 'teclats');
    
        if ($connexio ->connect_errno) {
            die("Error al conectar a la base de dades");
        }else{

        }
    
?>