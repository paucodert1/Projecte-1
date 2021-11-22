package src;
import java.sql.*;
import java.util.Scanner;


public class gestorDeInventari {

    // Variable globals

   public static Connection connexioBD=null;

    String[] proveidors = new String[60];
    int[] quantitatProd = new int [60];

    

    public static void main(String[] args) throws SQLException {

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
        System.out.println("2.- Gestio de Comandes");
        System.out.println("3.- Sortir");

        int opcio = teclatsScanner.nextInt();

        switch(opcio){

            case 1:
                GestioProdMenu();
            break;
    
            case 2:
            // GestioProdMenu();
            break;
    
            case 3:
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

        System.out.println("Codi_porta: ");
        String codi_porta = teclatsScanner.nextLine();

        String consulta = "INSERT INTO producte (NOM_PRODUCTE,MARCA,PREU,SWITCH,MIDA,RGB,CODI_PORTA) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta);

        sentencia.setString(1, nom_prod);
        sentencia.setString(2, marca);
        sentencia.setString(3, preu);
        sentencia.setString(4, keycaps);
        sentencia.setString(5, mida);
        sentencia.setString(6, rgb);
        sentencia.setString(7, codi_porta);
        
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


        String consulta_mod = "UPDATE producte SET NOM_PRODUCTE = ?, PREU = ?, CODI_PORTA = ? " +
                              "WHERE NOM_PRODUCTE = ?";

        PreparedStatement sentencia = connexioBD.prepareStatement(consulta_mod);

        sentencia.setString(1, nom_prod);
        sentencia.setString(2, preu);
        sentencia.setString(3, codi_porta);
        sentencia.setString(4, cerca_nom);

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
            System.out.print("Codi_porta: " + rs.getString("CODI_PORTA") + "\n ");
            
        }

        if (rs.next()){
            System.out.println("Producte consultat");

        }else{
            System.out.println("Error al consultar el producte ");
        }
        
    }

}







