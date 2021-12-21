<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Filtre Empresa</title>

    <link rel="stylesheet" href="css/estils_inv.css">
    <script src="js/menuv.js"></script>

</head>
<body>
<div class="all">

    <nav>
        <!-- Barra principal de la part superior -->
        <ul>
            <li><a href="#">&#8634;</a></li>
            <li><img src="Img/logo.png" alt="Logo"></li>
        </ul>

        <!-- Menu Vertical -->
        <div class="VMenu" id="VMenu">
            <a href="javascript:void(0)" class="TancarV" onclick="TancarMenu()">&times;</a> 

            <a href="index.html">Inici</a>

        </div>

        <div class="BMenu" id="BMenu">
            <button class="ObrirV" onclick="ObrirMenu()">&#9776;</button>
        </div>


    </nav> 
    <main>
        
            <div class="SQL">

                <h1>Inventari</h1>
                <h2 class="sub"><?php echo 'Éxit al conectar amb la BDD ' . $connexio->host_info?></h2>


                <input type="text" name="" id="cerca" placeholder="Cerca..." onkeyup="filterfunction()">

                <table id="taula">
                    <tr>
                        <th>Codi</th>
                        <th>NOM</th>
                        <th>Marca</th>
                        <th>Unitats</th>
                        <th>Preu</th>
                        <th>NIF Proveïdor</th>
                    </tr>

                    <?php foreach($result as $registre ):  ?>

                    <tr>
                        <td><?php echo $registre['CODI'];?></td>
                        <td><?php echo $registre['NOM_PRODUCTE'];?></td>
                        <td><?php echo $registre['Marca'];?></td>
                        <td><?php echo $registre['STOCK'];?></td>
                        <td><?php echo $registre['PREU'];?></td>
                        <td><?php echo $registre['CODI_PORTA'];?></td>
                    </tr>

                    <?php endforeach;?>

                </table>

            </div>

                <div class="formulari">
                    <form action="" method="post">

                        <label>Categoria</label>

                        <select name="cat">
                            <option value=""></option>
                            <option value="Slim">Slim</option>
                            <option value="Multimedia">Multimedia</option>
                            <option value="Mecanic">Mecanic</option>
                            <option value="Gaming">Gaming</option>
                            <option value="Mediacenter">Mediacenter</option>
                        </select>

                        <label>Material</label>

                        <select name="mat">
                            <option value=""></option>
                            <option value="ABS">ABS</option>
                            <option value="PBT">PBT</option>
                            <option value="POM">POM</option>
                            <option value="PPS">PPS</option>
                        </select>

                        <label>Ordenar</label>

                        <select name="ord">
                            <option value=""></option>
                            <option value="STOCK">STOCK</option>
                            <option value="CODI_PORTA">NIF</option>
                        </select>

                        <button name="buscar" type="submit">Buscar</button>
                    </form> 
   
                <div>

            </div>

        <script src="js/menufiltre.js"></script>

        
    </main>
    <footer>


    </footer>

</div>
</body>
</html>