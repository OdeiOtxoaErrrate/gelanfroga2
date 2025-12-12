package packmain;

import packmenu.Login;

public class MainSistema {
    public static void main(String[] args) {
    	//froga iruzkina
        Login login = new Login();
        String rol = login.login();

        if (rol != null) {
            SistemaFlujo.iniciarFlujo(rol);
        } else {
            System.out.println("aaaaaaaaaaaaaa");
        }
    }
}