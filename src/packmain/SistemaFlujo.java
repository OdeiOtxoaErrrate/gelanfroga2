package packmain;

import java.util.Scanner;
import packacciones.*;

public class SistemaFlujo {

    static Scanner sc = new Scanner(System.in);

    public static void iniciarFlujo(String rol) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nAukeratu taula:");
            System.out.println("1. Langileak");
            System.out.println("2. Bezeroak");
            System.out.println("3. Hornitzaileak");
            System.out.println("4. Produktuak");
            System.out.println("5. Salmenta");
            System.out.println("0. Irten");

            int tablaOpcion = sc.nextInt();
            sc.nextLine();

            switch (tablaOpcion) {
                case 1 : menuLangileak(rol); break;
                case 2 : menuBezeroak(rol); break;
                case 3 : menuHornitzaileak(rol); break;
                case 4 : menuProduktuak(rol); break;
                case 5 : menuSalmenta(rol); break;
                case 0 : salir = true; break;
                default : System.out.println("Aukera okerra.");
            }
        }

        System.out.println("Programa amaitu da.");
    }

    public static void menuLangileak(String rol) {
        LangileAcciones langile = new LangileAcciones();

        System.out.println("\nAukeratu ekintza:");
        System.out.println("1. Ikusi");
        System.out.println("2. Bilatu NAN bidez");
        System.out.println("3. Gehitu");
        System.out.println("4. Aldatu");
        System.out.println("5. Ezabatu");
        System.out.println("0. Itzuli");

        int ekintza = sc.nextInt();
        sc.nextLine();

        if (!tienePermiso(rol, "langileak", ekintza)) {
            System.out.println("Ez duzu baimenik ekintza hau egiteko.");
            return;
        }

        switch (ekintza) {
            case 1 : langile.selectgeneral(); break;
            case 2 : langile.selectpk(); break;
            case 3 : langile.insertlangile(); break;
            case 4 : langile.updatelangile(); break;
            case 5 : langile.deletelangile(); break;
            case 0 : System.out.println("Itzuli..."); break;
            default : System.out.println("Ekintza okerra."); break;
        }
    }

    public static void menuBezeroak(String rol) {
        BezeroakAcciones bezero = new BezeroakAcciones();

        System.out.println("\nAukeratu ekintza:");
        System.out.println("1. Ikusi");
        System.out.println("2. Bilatu NAN bidez");
        System.out.println("3. Gehitu");
        System.out.println("4. Aldatu");
        System.out.println("5. Ezabatu");
        System.out.println("0. Itzuli");

        int ekintza = sc.nextInt();
        sc.nextLine();

        if (!tienePermiso(rol, "bezeroak", ekintza)) {
            System.out.println("Ez duzu baimenik ekintza hau egiteko.");
            return;
        }

        switch (ekintza) {
            case 1 : bezero.selectgeneral(); break;
            case 2 : bezero.selectpk(); break;
            case 3 : bezero.insertbezero(); break;
            case 4 : bezero.updatebezero(); break;
            case 5 : bezero.deletebezero(); break;
            case 0 : System.out.println("Itzuli..."); break;
            default : System.out.println("Ekintza okerra."); break;
        }
    }

    public static void menuHornitzaileak(String rol) {
        HornitzaileAcciones horn = new HornitzaileAcciones();

        System.out.println("\nAukeratu ekintza:");
        System.out.println("1. Ikusi");
        System.out.println("2. Bilatu ID bidez");
        System.out.println("3. Gehitu");
        System.out.println("4. Aldatu");
        System.out.println("5. Ezabatu");
        System.out.println("0. Itzuli");

        int ekintza = sc.nextInt();
        sc.nextLine();

        if (!tienePermiso(rol, "hornitzaileak", ekintza)) {
            System.out.println("Ez duzu baimenik ekintza hau egiteko.");
            return;
        }

        switch (ekintza) {
            case 1 : horn.selectgeneral(); break;
            case 2 : horn.selectpk(); break;
            case 3 : horn.inserthornitzaile(); break;
            case 4 : horn.updatehornitzaile(); break;
            case 5 : horn.deletehornitzaile(); break;
            case 0 : System.out.println("Itzuli..."); break;
            default : System.out.println("Ekintza okerra.");
        }
    }

    public static void menuProduktuak(String rol) {
        ProduktuAcciones prod = new ProduktuAcciones();

        System.out.println("\nAukeratu ekintza:");
        System.out.println("1. Ikusi");
        System.out.println("2. Bilatu ID bidez");
        System.out.println("3. Gehitu");
        System.out.println("4. Aldatu");
        System.out.println("5. Ezabatu");
        System.out.println("0. Itzuli");

        int ekintza = sc.nextInt();
        sc.nextLine();

        if (!tienePermiso(rol, "produktuak", ekintza)) {
            System.out.println("Ez duzu baimenik ekintza hau egiteko.");
            return;
        }

        switch (ekintza) {
            case 1 : prod.selectgeneral(); break;
            case 2 : prod.selectpk(); break;
            case 3 : prod.insertproduktu(); break; 
            case 4 : prod.updateproduktu(); break;
            case 5 : prod.deleteproduktu(); break;
            case 0 : System.out.println("Itzuli..."); break;
             default : System.out.println("Ekintza okerra."); break;
        }
    }

    public static void menuSalmenta(String rol) {
        SalmentaAcciones sal = new SalmentaAcciones();

        System.out.println("\nAukeratu ekintza:");
        System.out.println("1. Ikusi");
        System.out.println("2. Bilatu ID bidez");
        System.out.println("3. Gehitu");
        System.out.println("4. Aldatu");
        System.out.println("5. Ezabatu");
        System.out.println("0. Itzuli");

        int ekintza = sc.nextInt();
        sc.nextLine();

        if (!tienePermiso(rol, "salmenta", ekintza)) {
            System.out.println("Ez duzu baimenik ekintza hau egiteko.");
            return;
        }

        switch (ekintza) {
            case 1: sal.selectgeneral(); break;
            case 2: sal.selectpk(); break;
            case 3 : sal.insertsalmenta(); break;
            case 4 : sal.updatesalmenta(); break;
            case 5 : sal.deletesalmenta(); break; 
            case 0 : System.out.println("Itzuli..."); break;
            default : System.out.println("Ekintza okerra."); break;
        }
    }

    public static boolean tienePermiso(String rol, String tabla, int ekintza) {
        switch (rol) {
            case "zuzendaria":
                return ekintza >= 1 && ekintza <= 5;

            case "soportea":
                if (tabla.equals("bezeroak")) return ekintza == 1 || ekintza == 4;
                if (tabla.equals("salmenta")) return ekintza == 1;
                break;

            case "teknikaria":
                if (tabla.equals("langileak") || tabla.equals("hornitzaileak")) return ekintza >= 1 && ekintza <= 5;
                if (tabla.equals("bezeroak")) return ekintza == 1;
                break;

            case "biltegia":
                if (tabla.equals("produktuak")) return ekintza >= 1 && ekintza <= 5;
                if (tabla.equals("salmenta") || tabla.equals("hornitzaileak")) return ekintza == 1;
                break;
        }

        return false;
    }
}