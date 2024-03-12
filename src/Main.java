import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[][] player = new String[12][3];
    public static int bons = 0;
    public static int llops = 0;


    public static void main(String[] args) {

        int menu = menu();


    }

    public static int menu() {
        Scanner input = new Scanner(System.in);

        int op = 0;
        String mort;

        System.out.println("Benvingut al joc del lobo de cachonegro\n" +
                "Seleccioneu una de les opcions disponibles per a jugar: \n" +
                "1. 6 jugadors (2 llops 4 pueblerins)\n 2. 8 jugadors (2 llops 5 pueblerins 1 vident)\n" +
                "3. 12 jugadors (3 llops 7 pueblerins 1 bruixa 1 vident)\n 4. Sortir ");
        op = input.nextInt();

        switch (op) {
            case 1:op1();
                do {
                    mort=nit();
                    System.out.println("El mort aquesta nit ha estat "+mort+", queden "+bons+" bons i "+llops+"llops");
                }while(bons>llops || llops!=0);
            case 2:op2();
                do {
                    mort=nit();
                    System.out.println("El mort aquesta nit ha estat "+mort+", queden "+bons+" bons i "+llops+"llops");
                }while(bons>llops || llops!=0);
            case 3:
            case 4:
                break;
            default:
                System.out.println("ERROR: Opció no vàlida!");
                menu(); // Cas recursiu
                break;
        }
        return op;
    }

    public static void op1() { //opció 1: 6 jugadors 2 llops 4 pueblerins
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int random1 = 0, random2 = 0, random3 = 0;
        for (int i = 0; i < 6; i++) {
            System.out.println("Introdueix el nom del jugador " + i + ": ");
            player[i][0] = input.next();
            player[i][2] = "viu";
        }
        bons=4;
        llops=2;

        do { //triar llops
            random1 = random.nextInt(6);
            random2 = random.nextInt(6);
        }while (random1==random2);

        for (int i=0; i<6; i++){
            if (i==random1 || i==random2){
                player[i][1]="Llop";
            } else {
                player[i][1]="Pueblerin";
            }
        }

        for (int i=0; i<6; i++){
            System.out.println(" Jugador "+player[i][0]+", quan no et vegi ningú escriu ok per saber el teu rol");
            String ok=input.next();
            System.out.println("Ets un "+player[i][1]);
            System.out.println("Escriu ok per pasar al seguent jugador");
            String okk=input.next();

            for (int j=0; j<15; j++){
                System.out.println("\n");
            }
        }
    }

    public static void op2() {

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int random1 = 0, random2 = 0, random3 = 0;
        for (int i = 0; i < 8; i++) {
            System.out.println("Introdueix el nom del jugador " + i + ": ");
            player[i][0] = input.next();
            player[i][1] = "0";
        }

        do {
            random1 = random.nextInt(8);
            random2 = random.nextInt(8);
        } while (random1 == random2);

        do {
            random3 = random.nextInt(8);
        } while (random3 == random1 || random3 == random2);
        for (int i = 0; i < 8; i++) {
            if (i == random1 || i == random2) {
                player[i][1] = "Llop";
            } else if (i == random3) {
                player[i][1] = "Vident";
            } else {
                player[i][1] = "Pueblerin";
            }
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(" Jugador " + player[i][0] + ", quan no et vegi ningú escriu ok per saber el teu rol");
            String ok = input.next();
            System.out.println("Ets un " + player[i][1]);
            System.out.println("Escriu ok per pasar al seguent jugador");
            String okk = input.next();
            for (int j=0; j<15; j++){
                System.out.println("\n");
            }
        }
    }
    public static String nit(){
        boolean jugador=false;
        String eliminat, rol;

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, i tant els llops com la vident tindran un minut per escollir amb qui utilitzar el seu rol");
        System.out.println("Quan la vident estigui sola per mirar el rol de algún jugador que cliqui ok");
        String okkk=input.next();

        do {
            System.out.println("Torn de la vident, aqui tenim una llista dels noms: ");
            for (int j = 0; j < 12; j++) {
                if (Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Llop") && Objects.equals(player[j][2], "Viu")) {
                    System.out.println(player[j][0]);
                }
            }
            System.out.println(" Escriu el nom del que vols saber el seu rol ");
            rol=input.next();
            for (int i=0; i<12; i++){
                if (Objects.equals(rol, player[i][0])){
                    System.out.println(player[i][1]);
                    System.out.println("Enrecordet i cuan sigui així clica ok");
                    okkk=input.next();
                    jugador=true;
                    break;
                } else if (i==11){
                    System.out.println("El nom que has introduit no coincideix amb els noms mostrats, siusplau intentiu de nou");
                }
            }
        }while(!jugador);
        jugador=false;
        for (int k=0; k<40;k++){
            System.out.println("\n");
        }

        do{
            System.out.println("Els jugador vius son aquests: ");
            for (int j=0; j<12; j++){
                if (Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Vident") && Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Introduiu el nom del que voleu matar: ");
            eliminat=input.next();

            for (int i=0; i<12; i++){
                if (Objects.equals(eliminat, player[i][0])){
                    player[i][2]="Mort";
                    bons=bons-1;
                    jugador=true;
                    break;
                } else if (i==11){
                    System.out.println("El nom que has introduit no coincideix amb els noms mostrats, siusplau intentiu de nou");
                }
            }
            for (int k=0; k<25;k++){
                System.out.println("\n");
            }
        }while (!jugador);
        return eliminat;
    }
}




