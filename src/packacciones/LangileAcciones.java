package packacciones;
import java.util.*;
import java.sql.*;
import packconexion.Conexion;
public class LangileAcciones {
	Conexion conexion = new Conexion();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;
	
	public void selectgeneral() {
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM langileak");
			
			while(rs.next()) {
				String izena = rs.getString(1);
				String abizena = rs.getString(2);
				String nan = rs.getString(3);
				String email = rs.getString(4);
				String erabiltzailea = rs.getString(5);
				String rol = rs.getString(6);
				int pasahitza = rs.getInt(7);
				System.out.println(izena +"-"+ abizena+"-"+nan+"-"+email+"-"+erabiltzailea+"-"+pasahitza+"-"+rol);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(cn != null) {
					cn.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public String selectpk() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sartu bilatu behar duzun langilearen NANa:");
		String pk = sc.nextLine();
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM langileak WHERE nan = '" +pk+"'");
			
			while(rs.next()) {
				String izena = rs.getString(1);
				String abizena = rs.getString(2);
				String nan = rs.getString(3);
				String email = rs.getString(4);
				String erabiltzailea = rs.getString(5);
				String rol = rs.getString(6);
				int pasahitza = rs.getInt(7);
				System.out.println(izena +"-"+ abizena+"-"+nan+"-"+email+"-"+erabiltzailea+"-"+pasahitza+"-"+rol);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(cn != null) {
					cn.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return pk;
	}
	public void updatelangile() {
		Scanner sc = new Scanner(System.in);
		String nan = selectpk();
		System.out.println("Aukeratu aldatu nahi duzuna (1-6):");
		System.out.println("1.izena");
		System.out.println("2.abizena");
		System.out.println("3.emaila");
		System.out.println("4.erabiltzailea");
		System.out.println("5.pasahitza");
		System.out.println("6.rola");
		int opcion = sc.nextInt();
		sc.nextLine();
		
		String zutabe;
		switch (opcion) {
		case 1: zutabe = "izena";
		break;
		case 2: zutabe = "abizena";
		break;
		case 3: zutabe = "email";
		break;
		case 4: zutabe = "erabiltzailea";
		break;
		case 5: zutabe = "pasahitza";
		break;
		case 6: zutabe = "rol";
		break;
		default:
			System.out.println("Aukeratu balio bat 1-etik 6ra.");
			return;
		}
		
		System.out.println("Sartu " +zutabe+ "-ren balio berria:");
	    String balioberri = sc.nextLine();

	    try {
	    	cn = conexion.conectar();
			stm = cn.createStatement();
			int zutabeak = stm.executeUpdate("UPDATE langileak SET " + zutabe + " = '" + balioberri + "' WHERE nan = '" + nan + "'");
			if (zutabeak > 0) {
				System.out.println("Eguneratzea arrakastatsua izan da.");
			} else {
				System.out.println("Ez da aldaketarik egin.");
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stm != null) {
	            	stm.close();
	            }
	            if (cn != null) {
	            	cn.close();
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }	
	}
	public void insertlangile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sartu langile berriaren izena:");
		String izena = sc.nextLine();
		System.out.println("Sartu langile berriaren abizena:");
		String abizena = sc.nextLine();
		System.out.println("Sartu langile berriaren NANa:");
		String nan = sc.nextLine();
		System.out.println("Sartu langile berriaren emaila:");
		String email = sc.nextLine();
		System.out.println("Sartu langile berriaren erabitlzailea:");
		String erabiltzailea = sc.nextLine();
		System.out.println("Sartu langile berriaren pasahitza:");
		int pasahitza = sc.nextInt();
		sc.nextLine();
		System.out.println("Sartu langile berriaren rola:");
		String rol = sc.nextLine();
		try {
			Connection cn = conexion.conectar();
			Statement stm = cn.createStatement();

			String sql = "INSERT INTO langileak (izena, abizena, nan, email, erabiltzailea, pasahitza, rol) VALUES ('"
					+ izena + "', '"
					+ abizena + "', '"
					+ nan + "', '"
					+ email + "', '"
					+ erabiltzailea + "', '"
					+ pasahitza + "', '"
					+ rol + "')";

			int filas = stm.executeUpdate(sql);
			if (filas > 0) {
				System.out.println("langilea ondo gehitu da!");
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
	public void deletelangile() {
		Scanner sc = new Scanner(System.in);
		String nan = selectpk();
		System.out.println("Ziur zaude langilea ezabatu nahi duzula? (bai/ez)");
		String erantzuna = sc.nextLine().trim().toLowerCase();

		if (erantzuna.equals("bai")) {
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				String sql = "DELETE FROM langileak WHERE nan = '" + nan + "'";
				int rowsAffected = stm.executeUpdate(sql);

				if (rowsAffected > 0) {
					System.out.println("Langilea arrakastaz ezabatu da.");
				} else {
					System.out.println("Ez da langilerik aurkitu NAN horrekin.");
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