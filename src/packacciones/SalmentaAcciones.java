package packacciones;

import java.sql.*;
import java.util.Scanner;
import packconexion.Conexion;

public class SalmentaAcciones {
    Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;

    public void selectgeneral() {
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM salmentak");

            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("data");
                String gailua = rs.getString("gailua");
                String eroslea = rs.getString("eroslea");
                int garantía = rs.getInt("garantía");

                System.out.println(id + "-" + data + "-" + gailua + "-" + eroslea + "-" + garantía);
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
        System.out.println("Sartu bilatu behar duzun salmentaren IDa:");
        String pk = sc.nextLine();
        try {
        	cn = conexion.conectar();
        	stm = cn.createStatement();
        	rs = stm.executeQuery("SELECT * FROM salmentak WHERE id = '" + pk + "'");

        	while (rs.next()) {
            String data = rs.getString("data");
            String gailua = rs.getString("gailua");
            String eroslea = rs.getString("eroslea");
            int garantía = rs.getInt("garantía");

            System.out.println(pk + "-" + data + "-" + gailua + "-" + eroslea + "-" + garantía);
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

    public void updatesalmenta() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Aukeratu aldatu nahi duzuna (1-4):");
        System.out.println("1. Data");
        System.out.println("2. Gailua");
        System.out.println("3. Eroslea");
        System.out.println("4. Garantía");

        int opcion = sc.nextInt();
        sc.nextLine();

        String zutabe;
        switch (opcion) {
            case 1: zutabe = "data"; break;
            case 2: zutabe = "gailua"; break;
            case 3: zutabe = "eroslea"; break;
            case 4: zutabe = "garantía"; break;
            default:
                System.out.println("Aukeratu balio bat 1-etik 4ra.");
                return;
        }

        System.out.println("Sartu " + zutabe + "-ren balio berria:");
        String balioberri = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            int zutabeak = stm.executeUpdate("UPDATE salmentak SET " + zutabe + " = '" + balioberri + "' WHERE id = '" + id + "'");
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

    public void insertsalmenta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu salmenta berriaren data (YYYY-MM-DD):");
        String data = sc.nextLine();
        System.out.println("Sartu salmenta berriaren gailua:");
        String gailua = sc.nextLine();
        System.out.println("Sartu salmenta berriaren eroslea:");
        String eroslea = sc.nextLine();
        System.out.println("Sartu salmenta berriaren garantía (urteetan):");
        String garantía = sc.nextLine();

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            String sql = "INSERT INTO salmentak (data, gailua, eroslea, garantía) VALUES ('"
                    + data + "', '"
                    + gailua + "', '"
                    + eroslea + "', '"
                    + garantía + "')";

            int filas = stm.executeUpdate(sql);
            if (filas > 0) {
                System.out.println("Salmenta ondo gehitu da!");
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

    public void deletesalmenta() {
        Scanner sc = new Scanner(System.in);
        String id = selectpk();
        System.out.println("Ziur zaude salmenta ezabatu nahi duzula? (bai/ez)");
        String erantzuna = sc.nextLine().trim().toLowerCase();

        if (erantzuna.equals("bai")) {
            try {
                cn = conexion.conectar();
                stm = cn.createStatement();
                String sql = "DELETE FROM salmentak WHERE id = '" + id + "'";
                int rowsAffected = stm.executeUpdate(sql);

                if (rowsAffected > 0) {
                    System.out.println("Salmenta arrakastaz ezabatu da.");
                } else {
                    System.out.println("Ez da salmentarik aurkitu ID horrekin.");
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