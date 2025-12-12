package packacciones;
import java.sql.*;
import java.util.Scanner;
import packconexion.Conexion;

public class HornitzaileAcciones {
    Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;

    public void selectgeneral() {
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM hornitzaileak");

            while (rs.next()) {
                int id = rs.getInt("id");
                String enpresaren_izena = rs.getString("enpresaren_izena");
                String saltzen_duen_produktua = rs.getString("saltzen_duen_produktua");
                String telefono_zenbakia = rs.getString("telefono_zenbakia");
                String emaila = rs.getString("emaila");
                String ordainketa_metodoa = rs.getString("ordainketa_metodoa");
                System.out.println(id + "-" + enpresaren_izena + "-" + saltzen_duen_produktua + "-" + telefono_zenbakia + "-" + emaila + "-" + ordainketa_metodoa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public String selectpk() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu bilatu behar duzun hornitzailearen IDa:");
        String pk = sc.nextLine();
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM hornitzaileak WHERE id = '" + pk + "'");

            while (rs.next()) {
                String enpresaren_izena = rs.getString("enpresaren_izena");
                String saltzen_duen_produktua = rs.getString("saltzen_duen_produktua");
                String telefono_zenbakia = rs.getString("telefono_zenbakia");
                String emaila = rs.getString("emaila");
                String ordainketa_metodoa = rs.getString("ordainketa_metodoa");

                System.out.println(pk + "-" + enpresaren_izena + "-" + saltzen_duen_produktua + "-" + telefono_zenbakia + "-" + emaila + "-" + ordainketa_metodoa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return pk;
    }

    public void updatehornitzaile() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Aukeratu aldatu nahi duzuna (1-5):");
        System.out.println("1. Enpresaren izena");
        System.out.println("2. Saltzen duen produktua");
        System.out.println("3. Emaila");
        System.out.println("4. Telefono zenbakia");
        System.out.println("5. Ordainketa metodoa");

        int opcion = sc.nextInt();
        sc.nextLine();

        String zutabe;
        switch (opcion) {
            case 1: zutabe = "enpresaren_izena"; break;
            case 2: zutabe = "saltzen_duen_produktua"; break;
            case 3: zutabe = "emaila"; break;
            case 4: zutabe = "telefono_zenbakia"; break;
            case 5: zutabe = "ordainketa_metodoa"; break;
            default:
                System.out.println("Aukeratu balio bat 1-etik 5ra.");
                return;
        }

        System.out.println("Sartu " + zutabe + "-ren balio berria:");
        String balioberri = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            int zutabeak = stm.executeUpdate("UPDATE hornitzaileak SET " + zutabe + " = '" + balioberri + "' WHERE id = '" + id + "'");
			if (zutabeak > 0) {
				System.out.println("Eguneratzea arrakastatsua izan da.");
			} else {
				System.out.println("Ez da aldaketarik egin.");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void inserthornitzaile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu hornitzaile berriaren enpresaren izena:");
        String enpresaren_izena = sc.nextLine();
        System.out.println("Sartu hornitzaile berriak saltzen duen produktua:");
        String saltzen_duen_produktua = sc.nextLine();
        System.out.println("Sartu hornitzaile berriaren telefono zenbakia:");
        String telefono_zenbakia = sc.nextLine();
        System.out.println("Sartu hornitzaile berriaren emaila:");
        String emaila = sc.nextLine();
        System.out.println("Sartu hornitzaile berriaren ordainketa metodoa:");
        String ordainketa_metodoa = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            String sql = "INSERT INTO hornitzaileak (enpresaren_izena, saltzen_duen_produktua, telefono_zenbakia, emaila, ordainketa_metodoa) VALUES ('"
                    + enpresaren_izena + "', '"
                    + saltzen_duen_produktua + "', '"
                    + telefono_zenbakia + "', '"
                    + emaila + "', '"
                    + ordainketa_metodoa + "')";

            int filas = stm.executeUpdate(sql);
            if (filas > 0) {
                System.out.println("Hornitzailea ondo gehitu da!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void deletehornitzaile() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Ziur zaude hornitzailea ezabatu nahi duzula? (bai/ez)");
        String erantzuna = sc.nextLine().trim().toLowerCase();

        if (erantzuna.equals("bai")) {
            try {
                cn = conexion.conectar();
                stm = cn.createStatement();
                String sql = "DELETE FROM hornitzaileak WHERE id = '" + id + "'";
                int rowsAffected = stm.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("Hornitzailea arrakastaz ezabatu da.");
                } else {
                    System.out.println("Ez da hornitzailerik aurkitu ID horrekin.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stm != null) stm.close();
                    if (cn != null) cn.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            System.out.println("Ezabaketa bertan behera utzi da.");
        }
    }
}