package packacciones;

import java.sql.*;
import java.util.Scanner;
import packconexion.Conexion;

public class ProduktuAcciones {
    Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;

    public void selectgeneral() {
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM produktuak");

            while (rs.next()) {
                int id = rs.getInt("id");
                String mota = rs.getString("produktu_mota");
                String marka = rs.getString("marka");
                String modeloa = rs.getString("modeloa");
                int urtea = rs.getInt("urtea");
                String egoera = rs.getString("egoera");
                String hornitzailea = rs.getString("hornitzailea");

                System.out.println(id + "-" + mota + "-" + marka + "-" + modeloa + "-" + urtea + "-" + egoera + "-" + hornitzailea);
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
        System.out.println("Sartu bilatu behar duzun produkturen IDa:");
        String pk = sc.nextLine();
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM produktuak WHERE id = '" + pk + "'");

            while (rs.next()) {
                String mota = rs.getString("produktu_mota");
                String marka = rs.getString("marka");
                String modeloa = rs.getString("modeloa");
                int urtea = rs.getInt("urtea");
                String egoera = rs.getString("egoera");
                String hornitzailea = rs.getString("hornitzailea");

                System.out.println(pk + "-" + mota + "-" + marka + "-" + modeloa + "-" + urtea + "-" + egoera + "-" + hornitzailea);
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

    public void updateproduktu() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Aukeratu aldatu nahi duzuna (1-6):");
        System.out.println("1. Produktu mota");
        System.out.println("2. Marka");
        System.out.println("3. Modeloa");
        System.out.println("4. Urtea");
        System.out.println("5. Egoera");
        System.out.println("6. Hornitzailea");

        int opcion = sc.nextInt();
        sc.nextLine(); 

        String zutabe;
        switch (opcion) {
            case 1: zutabe = "produktu_mota"; break;
            case 2: zutabe = "marka"; break;
            case 3: zutabe = "modeloa"; break;
            case 4: zutabe = "urtea"; break;
            case 5: zutabe = "egoera"; break;
            case 6: zutabe = "hornitzailea"; break;
            default:
                System.out.println("Aukeratu balio bat 1-etik 6ra.");
                return;
        }

        System.out.println("Sartu " + zutabe + "-ren balio berria:");
        String balioberri = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            int zutabeak = stm.executeUpdate("UPDATE produktuak SET " + zutabe + " = '" + balioberri + "' WHERE id = '" + id + "'");
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

    public void insertproduktu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu produktu berriaren mota:");
        String mota = sc.nextLine();
        System.out.println("Sartu produktu berriaren marka:");
        String marka = sc.nextLine();
        System.out.println("Sartu produktu berriaren modeloa:");
        String modeloa = sc.nextLine();
        System.out.println("Sartu produktu berriaren urtea:");
        String urtea = sc.nextLine();
        System.out.println("Sartu produktu berriaren egoera:");
        String egoera = sc.nextLine();
        System.out.println("Sartu produktu berriaren hornitzailea:");
        String hornitzailea = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            String sql = "INSERT INTO produktuak (produktu_mota, marka, modeloa, urtea, egoera, hornitzailea) VALUES ('"
                    + mota + "', '"
                    + marka + "', '"
                    + modeloa + "', '"
                    + urtea + "', '"
                    + egoera + "', '"
                    + hornitzailea + "')";

            int filas = stm.executeUpdate(sql);
            if (filas > 0) {
                System.out.println("Produktua ondo gehitu da!");
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

    public void deleteproduktu() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Ziur zaude produktua ezabatu nahi duzula? (bai/ez)");
        String erantzuna = sc.nextLine().trim().toLowerCase();

        if (erantzuna.equals("bai")) {
            try {
                cn = conexion.conectar();
                stm = cn.createStatement();
                String sql = "DELETE FROM produktuak WHERE id = '" + id + "'";
                int rowsAffected = stm.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("Produktua arrakastaz ezabatu da.");
                } else {
                    System.out.println("Ez da produkturik aurkitu ID horrekin.");
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