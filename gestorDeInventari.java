package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class gestorDeInventari {

    // Variable globals

   public static Connection connexioBD=null;

    String[] proveidors = new String[60];
    int[] quantitatProd = new int [60];

    // Constant

    static final String DIREC_PENDENTS = "files/Entrades_Pendents/";
    static final String DIREC_PROCESSADES = "files/Entrades_Processades/";
    static final String COMANDA_STRING="#############-Comanda-############# \n Empresa Imaginaria KS 123 \n Telefon: +927177858 \n ____________________________________";

    

    public static void main(String[] args) throws SQLException, IOException {

        // Variables

        Scanner teclatsScanner = new Scanner(System.in);
        boolean sortir=false;

        // Connectem a la BD 

        connexioBD();

        // MENU

        do{

        System.out.println("MENU PRINCIPAL");
        System.out.println("Escull una opcio: ");
        System.out.println("1.- Gestio de productes");
        System.out.println("2.- Actualitzacio Stock");
        System.out.println("3.- Generacio comandes");
        System.out.println("4.- Analisis comandes proveidor");
        System.out.println("5.- Sortir");
        System.out.println("\nTRIA UNA OPCIÓ: ");

        int opcio = teclatsScanner.nextInt();

        switch(opcio){

            case 1:
                GestioProdMenu();
            break;
    
            case 2:
                ActualitzacioStock();
            break;

            case 3:
                GeneracioDeComandes();
            break;
    
            case 4:
                GeneracioDeComandesV2();
            break;

            case 5:
            sortir=true;
            break;
    
            default:
            System.out.println("Opcio no valida");
            }

        } while(!sortir);

        // desconexioBD()
    } 
    
    // FORA DEL MAIN 

    public static void connexioBD(){

        String servidor="jdbc:mysql://localhost:3306/";
        String bbdd="teclats";
        String user="root";
        String password="Fat/3232";
        try {
            connexioBD = DriverManager.getConnection(servidor + bbdd, user, password);
            System.out.println("Connexio amb exit");
        } catch (SQLException ex) {
            System.out.println("No sa pogut connectar");
            ex.printStackTrace();
            
        }
    }

    static void GestioProdMenu () throws SQLException{

        boolean sortir=false;
        Scanner teclatsScanner = new Scanner(System.in);


        System.out.println("1. LLista tots els productes");
        System.out.println("2. Alta prductes");
        System.out.println("3. Modifica producte");
        System.out.println("4. Esborra productes");
        System.out.println("5. Consulta productes");

        System.out.println("TRIA OPCIO");

        int opcio = teclatsScanner.nextInt();

        switch(opcio){

            case 1:
                LListaTotsProductes();
            break;
    
            case 2:
                AltaProductes();
            break;
    
            case 3:
                ModificaProductes();
            break;

            case 4:
                EsborraProductes();
            break;

            case 5:
                ConsultarProductes();
            break;

            case 6:
            sortir=true;
            default:
            System.out.println("Opcio no valida");
            }

    }

    static void LListaTotsProductes() throws SQLException{

        System.out.println("Llistar tots els productes");

        String consulta="SELECT * FROM producte";

        PreparedStatement ps = connexioBD.prepareStatement(consulta);

        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            System.out.print("Codi: " + rs.getString("CODI") + " ");
            System.out.print("Marca: " + rs.getString("MARCA") + " ");
            System.out.print("Preu: " + rs.getString("PREU") + " ");
            System.out.print("Switch: " + rs.getString("SWITCH") + " ");
            System.out.print("Mida: " + rs.getString("MIDA") + " ");
            System.out.print("Rgb: " + rs.getString("RGB") + " ");
            System.out.print("Stock: " + rs.getString("STOCK") + " ");
            System.out.print("Codi_porta: " + rs.getString("CODI_PORTA") + "\n");
            
        }

    }

    static void AltaProductes() throws SQLException{

        Scanner teclatsScanner = new Scanner(System.in);

        System.out.println("Nom_producte: ");
        String nom_prod = teclatsScanner.nextLine();

        System.out.println("Marca: ");
        String marca = teclatsScanner.nextLine();

        System.out.println("Preu: ");
        String preu = teclatsScanner.nextLine();

        System.out.println("Switch: ");
        String keycaps = teclatsScanner.nextLine();

        System.out.println("Mida: ");
        String mida = teclatsScanner.nextLine();

        System.out.println("RGB: ");
        String rgb = teclatsScanner.nextLine();

        System.out.println("STOCK: ");
        String stock = teclatsScanner.nextLine();

        System.out.println("Codi_porta: ");
        String codi_porta = teclatsScanner.nextLine();

        String consulta = "INSERT INTO producte (NOM_PRODUCTE,MARCA,PREU,SWITCH,MIDA,RGB,STOCK,CODI_PORTA) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta);

        sentencia.setString(1, nom_prod);
        sentencia.setString(2, marca);
        sentencia.setString(3, preu);
        sentencia.setString(4, keycaps);
        sentencia.setString(5, mida);
        sentencia.setString(6, rgb);
        sentencia.setString(7, stock);
        sentencia.setString(8, codi_porta);
        
        if (sentencia.executeUpdate() !=0){
            System.out.println("Producte donat d' alta");

        }else{
            System.out.println("Error al donar d' alta el producte");
        }

    }

    static void ModificaProductes () throws SQLException{

        Scanner teclatsScanner = new Scanner(System.in);

        System.out.println("Modificar dades: ");

        System.out.println("Nom de cerca");
        String cerca_nom = teclatsScanner.nextLine();

        System.out.println("ARA INTRODUEIX ELS NOUS CAMPS DEL PRODUCTE QUE VOLS MODIFICAR\n");

        System.out.println("Nom nou producte: ");
        String nom_prod = teclatsScanner.nextLine();

        System.out.println("Preu nou: ");
        String preu = teclatsScanner.nextLine();

        System.out.println("Codi_porta nou: ");
        String codi_porta = teclatsScanner.nextLine();

        System.out.println("Stock nou: ");
        String stock = teclatsScanner.nextLine();

        String consulta_mod = "UPDATE producte SET NOM_PRODUCTE = ?, PREU = ?, CODI_PORTA = ?, STOCK = ? " +
                              "WHERE NOM_PRODUCTE = ?";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta_mod);

        sentencia.setString(1, nom_prod);
        sentencia.setString(2, preu);
        sentencia.setString(3, codi_porta);
        sentencia.setString(4, stock);
        sentencia.setString(5, cerca_nom);

        if (sentencia.executeUpdate() !=0){
            System.out.println("Producte cambiat correctament");

        }else{
            System.out.println("Error al cambiar el producte");
        }

    }

    static void EsborraProductes () throws SQLException{

        Scanner teclatsScanner = new Scanner(System.in);

        System.out.println("Esborra productes: ");

        System.out.println("Nom de el producte que vols borrar: ");
        String nom_prod = teclatsScanner.nextLine();


        String consulta_del = "DELETE FROM producte WHERE NOM_PRODUCTE = ?";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta_del);

        sentencia.setString(1, nom_prod);

        if (sentencia.executeUpdate() !=0){
            System.out.println("Producte borrat correctament");

        }else{
            System.out.println("Error al borrar el producte");
        }
    }

        
    static void ConsultarProductes() throws SQLException{
        
        Scanner teclatsScanner = new Scanner(System.in);

        System.out.println("Consulta d'un producte");

        System.out.println("Nom de producte a consultar: ");
        String consult = teclatsScanner.nextLine();

        String consulta_sel = "SELECT * FROM producte WHERE NOM_PRODUCTE=?";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta_sel);

        sentencia.setString(1, consult);

        ResultSet rs=sentencia.executeQuery();

        while(rs.next()){

            System.out.print("Codi: " + rs.getString("CODI") + " ");
            System.out.print("Marca: " + rs.getString("MARCA") + " ");
            System.out.print("Preu: " + rs.getString("PREU") + " ");
            System.out.print("Switch: " + rs.getString("SWITCH") + " ");
            System.out.print("Mida: " + rs.getString("MIDA") + " ");
            System.out.print("Rgb: " + rs.getString("RGB") + " ");
            System.out.print("Stock: " + rs.getString("STOCK") + " ");
            System.out.print("Codi_porta: " + rs.getString("CODI_PORTA") + "\n ");
            
        }

        if (rs.next()){
            System.out.println("Producte consultat");

        }else{
            System.out.println("Error al consultar el producte ");
        }
        
    }

    static void ActualitzacioStock() throws SQLException, IOException{

        System.out.println("ACTUALITZACIO DE STOCK");

        File directoriPen = new File(DIREC_PENDENTS);
        File directoriPro= new File(DIREC_PROCESSADES);

        if (directoriPen.isDirectory()){

            File[] fitxers = directoriPen.listFiles();

            for(int i=0;i<fitxers.length;i++){
                
                UpdateStock(fitxers[i]);
                MoureProcessades(fitxers[i], directoriPro, directoriPro);
            }
        }

    }

    static void UpdateStock(File directoriPen) throws SQLException, IOException{

        FileReader reader = new FileReader(directoriPen);

            BufferedReader buffer=new BufferedReader(reader);

            String linia;
            while((linia=buffer.readLine()) != null){

                int Sep=linia.indexOf(":");

                int numero_prod = Integer.parseInt(linia.substring(0,Sep)); 
                System.out.println("El codi producte es: " + numero_prod);

                String unitats_prod = linia.substring(Sep + 1);
                System.out.println("El numero de unitats es: " + unitats_prod);
                
                String Update = "UPDATE producte SET STOCK=STOCK+? WHERE CODI=?";
                PreparedStatement sentencia = connexioBD.prepareStatement(Update);

                sentencia.setString(1,unitats_prod);
                sentencia.setInt(2, numero_prod);
                sentencia.executeUpdate();

                if (sentencia.executeUpdate() !=0){
                    System.out.println("Producte actualitzat correctament");
        
                }else{
                    System.out.println("Error al actualitzar el producte");
                }
            

            }
            buffer.close();
            reader.close();
    
    }

    static void MoureProcessades(File directoriPen, File directoriPro, File fitxers) throws IOException{
        
        FileSystem sistemFicheros = FileSystems.getDefault();
        Path origen = sistemFicheros.getPath(DIREC_PENDENTS + directoriPen.getName());
        Path desti = sistemFicheros.getPath(DIREC_PROCESSADES +directoriPen.getName());
        
        Files.move(origen,desti,StandardCopyOption.REPLACE_EXISTING);
        System.out.println("S' ha mogut a processat el fitxer: " + directoriPen.getName());
    

    }

    static void GeneracioDeComandes () throws SQLException{

        String consulta="SELECT * FROM producte WHERE STOCK<150 order by CODI_PORTA";

        PreparedStatement ps = connexioBD.prepareStatement(consulta);

        ResultSet rs=ps.executeQuery();

        String proveidorAnt="";

        while(rs.next()){
            String proveidor=rs.getString("CODI_PORTA");
            int codi=rs.getInt("CODI");
            
            if (!proveidorAnt.equals(proveidor)){
        
                System.out.println(codi + "Canvi de proveidor " + proveidor);

            }else{
                System.out.println("Estat erroni");
            }
        }
       
    }

    static void GeneracioDeComandesV2 () throws SQLException, IOException{

        System.out.println("GENERACIO DE COMANDES");
        
        System.out.println("Creem el fitxer de comanda");

        // PREPAREM COMANDA

        String consulta="SELECT * FROM producte WHERE STOCK<150 order by CODI_PORTA";

        PreparedStatement ps = connexioBD.prepareStatement(consulta);

        // LLANÇEM LA COMANDA

        ResultSet rs=ps.executeQuery();

        // OBJ DATA 

        LocalDate date = LocalDate.now();
        String proveidorAnt="";

        if(rs.next()){

            String actproveidor=rs.getString("CODI_PORTA");

            String proveidor=rs.getString("CODI_PORTA");
            int codi=rs.getInt("CODI");

            System.out.println("El producte amb el codi: " + codi + " " +  "canvie el proveidor: " + proveidor);

            FileWriter fw = new FileWriter("Projecte 1/files/Comanda/"+ proveidor + "_" + date + "txt4",true);
            BufferedWriter bf = new BufferedWriter(fw);
            PrintWriter escritor = new PrintWriter(bf);
            
            escritor.println(COMANDA_STRING);

            do{

                escritor.println(" Codi: " + rs.getString("CODI")+ " Unitats: " + rs.getString("STOCK"));

                
    
            }while(rs.next());

            escritor.close();

        }
       
    }

}


 // String UpdateSel = "SELECT STOCK FROM teclats";
// PreparedStatement sentencia = connexioBD.prepareStatement(UpdateSel);

// String Update = "UPDATE teclats SET STOCK=STOCK+?";
// PreparedStatement sentenciaUpdate = connexioBD.prepareStatement(Update);







