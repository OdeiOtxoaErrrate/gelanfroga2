package packacciones;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import packconexion.Conexion;
public class BezeroakAcciones {
	Conexion conexion = new Conexion();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;
	
	public void selectgeneral() {
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM bezeroak");
			
			while(rs.next()) {
				String izena = rs.getString(1);
				String abizena = rs.getString(2);
				String nan = rs.getString(3);
				String email = rs.getString(4);
				String telefonoa = rs.getString(5);
				String helbidea = rs.getString(6);
				Date jaiotze_data = rs.getDate(7);
				String textarea = rs.getString(8);
				System.out.println(izena +"-"+ abizena+"-"+nan+"-"+email+"-"+telefonoa+"-"+jaiotze_data+"-"+helbidea+"-"+textarea);
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
		System.out.println("Sartu bilatu behar duzun bezeroaren NANa:");
		String pk = sc.nextLine();
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM bezeroak WHERE nan = '" +pk+"'");
			
			while(rs.next()) {
				String izena = rs.getString(1);
				String abizena = rs.getString(2);
				String nan = rs.getString(3);
				String email = rs.getString(4);
				String telefonoa = rs.getString(5);
				String helbidea = rs.getString(6);
				Date jaiotze_data = rs.getDate(7);
				String textarea = rs.getString(8);
				System.out.println(izena +"-"+ abizena+"-"+nan+"-"+email+"-"+telefonoa+"-"+jaiotze_data+"-"+helbidea+"-"+textarea);
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
	public void updatebezero() {
		Scanner sc = new Scanner(System.in);
		String nan = selectpk();
		System.out.println("Aukeratu aldatu nahi duzuna (1-8):");
		System.out.println("1.izena");
		System.out.println("2.abizena");
		System.out.println("3.nan");
		System.out.println("4.emaila");
		System.out.println("5.telefonoa");
		System.out.println("6.helbidea");
		System.out.println("7.jaiotze_data");
		System.out.println("8.textarea");
		int opcion = sc.nextInt();
		sc.nextLine();
		
		
		String zutabe;
		switch (opcion) {
		case 1: zutabe = "izena";
		break;
		case 2: zutabe = "abizena";
		break;
		case 3: zutabe = "nan";
		break;
		case 4: zutabe = "email";
		break;
		case 5: zutabe = "telefonoa";
		break;
		case 6: zutabe = "helbidea";
		break;
		case 7: zutabe = "jaiotze_data";
		break;
		case 8: zutabe ="textarea";
		break;
		default:
			System.out.println("Aukeratu balio bat 1-etik 8ra.");
			return;
		}
		
		System.out.println("Sartu " +zutabe+ "-ren balio berria:");
	    String balioberri = sc.nextLine();

	    try {
	    	cn = conexion.conectar();
			stm = cn.createStatement();
			int zutabeak = stm.executeUpdate("UPDATE bezeroak SET " + zutabe + " = '" + balioberri + "' WHERE nan = '" + nan + "'");
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
	public void insertbezero() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sartu bezero berriaren izena:");
		String izena = sc.nextLine();
		System.out.println("Sartu bezero berriaren abizena:");
		String abizena = sc.nextLine();
		System.out.println("Sartu bezero berriaren NANa:");
		String nan = sc.nextLine();
		System.out.println("Sartu bezero berriaren emaila:");
		String email = sc.nextLine();
		System.out.println("Sartu bezero berriaren telefonoa:");
		String telefonoa = sc.nextLine();
		System.out.println("Sartu bezero berriaren helbidea:");
		String helbidea = sc.nextLine();
		System.out.println("Sartu bezeroaren jaiotze data (YYYY-MM-DD):");
		String jaiotze_data = sc.nextLine();
		System.out.println("Sartu bezero berriaren textarea:");
		String textarea = sc.nextLine();
		
		try {
			Connection cn = conexion.conectar();
			Statement stm = cn.createStatement();

			String sql = "INSERT INTO bezeroak (izena, abizena, nan, email, telefonoa, helbidea, jaiotze_data, textarea ) VALUES ('"
					+ izena + "', '"
					+ abizena + "', '"
					+ nan + "', '"
					+ email + "', '"
					+ telefonoa + "', '"
					+ helbidea + "', '"
					+ jaiotze_data + "', '"
					+ textarea + "')";

			int filas = stm.executeUpdate(sql);
			if (filas > 0) {
				System.out.println("bezeroa ondo gehitu da!");
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
	public void deletebezero() {
		Scanner sc = new Scanner(System.in);
		String nan = selectpk();
		System.out.println("Ziur zaude bezeroa ezabatu nahi duzula? (bai/ez)");
		String erantzuna = sc.nextLine().trim().toLowerCase();

		if (erantzuna.equals("bai")) {
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				String sql = "DELETE FROM bezeroak WHERE nan = '" + nan + "'";
				int rowsAffected = stm.executeUpdate(sql);

				if (rowsAffected > 0) {
					System.out.println("Bezeroa arrakastaz ezabatu da.");
				} else {
					System.out.println("Ez da bezerorik aurkitu NAN horrekin.");
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
