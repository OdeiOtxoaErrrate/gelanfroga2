package packmenu;

import java.sql.*;
import java.util.Scanner;
import packconexion.Conexion;

public class Login {
    Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;

    public String login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu erabiltzailea:");
        String erabiltzailea = sc.nextLine();
        System.out.println("Sartu pasahitza:");
        int pasahitza = sc.nextInt();
        sc.nextLine(); 

        String rol = null;

        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            String sql = "SELECT rol FROM langileak WHERE erabiltzailea = '" + erabiltzailea + "' AND pasahitza = " + pasahitza;
            rs = stm.executeQuery(sql);

            if (rs.next()) {
                rol = rs.getString("rol");
                System.out.println("Ongi etorri, zure rola da: " + rol);
            } else {
                System.out.println("Erabiltzailea edo pasahitza okerra.");
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

        return rol;
    }
}
