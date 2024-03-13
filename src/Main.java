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
        boolean end = false;

        System.out.println("Benvingut al joc del lobo de cachonegro\n" +
                "Seleccioneu una de les opcions disponibles per a jugar: \n" +
                "1. 6 jugadors (2 llops 4 pueblerins)\n 2. 8 jugadors (2 llops 5 pueblerins 1 vident)\n" +
                "3. 12 jugadors (3 llops 7 pueblerins 1 bruixa 1 vident)\n 4. Sortir ");
        op = input.nextInt();

        switch (op) {
            case 1:op1();
                do {
                    mort=nitop1();
                    System.out.println("El mort aquesta nit ha estat "+mort+", queden "+bons+" bons i "+llops+"llops");

                    diaop1();

                    if (bons==llops){
                        System.out.println("Victòria dels llops, enhorabona, aaauuuuu");
                        end=true;
                    } else if (llops==0){
                        System.out.println("Victòria dels pueblerins, enhorabona!!");
                        end=true;
                    } else {
                        System.out.println("Torna la nit, tothom a dormir");
                    }
                }while(!end);
            case 2:
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
            player[i][2] = "Viu";
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
    public static String nitop1(){
        boolean jugador=false;
        String eliminat, rol;

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, els llops tindran un minut per escollir a qui matar");
        for (int j = 0; j < 6; j++) {
            if (Objects.equals(player[j][1], "Pueblerin")  && Objects.equals(player[j][2], "Viu") || Objects.equals(player[j][1], "Vident") && Objects.equals(player[j][2], "Viu")) {
                System.out.println(player[j][0]);
            }
        }


        do{
            System.out.println("Introduiu el nom del que voleu matar: ");
            eliminat=input.next();

            for (int i=0; i<6; i++){
                if (Objects.equals(eliminat, player[i][0])){
                    player[i][2]="Mort";
                    bons=bons-1;
                    jugador=true;
                    break;
                } else if (i==5){
                    System.out.println("El nom que has introduit no coincideix amb els noms mostrats, siusplau intentiu de nou");
                }
            }
            for (int k=0; k<25;k++){
                System.out.println("\n");
            }
        }while (!jugador);
        return eliminat;
    }


    public static void diaop1(){
        boolean jugador=false;
        String linchat, okkkk;
        Scanner input=new Scanner(System.in);

        System.out.println("Ara que ja es de dia, es el torn de les discussions, preneu-vos el vostre temps");
        do{
            System.out.println("Aquí teniu una llista de qui queda viu");
            for (int j=0; j<6; j++){
                if (Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu el nom de la persona que serà sacrificada: ");
            linchat=input.next();
            for (int i=0; i<6; i++){
                if (Objects.equals(linchat, player[i][0])){
                    player[i][2]="Mort";
                    jugador=true;
                    if (Objects.equals("Llop", player[i][1])){
                        llops=llops-1;
                        System.out.println("El jugador "+player[i][0]+" era un llop! Ja només queden "+llops+" llop");
                        okkkk=input.next();
                    } else if (Objects.equals("Pueblerin", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era un pueblerin! Ara queden "+bons+" bons");
                        okkkk=input.next();
                    }
                    break;
                } else if (i==5){
                    System.out.println("El nom introduït no es troba o està malament escrit");
                }
            }
            for (int k=0; k<25;k++){
                System.out.println("\n");
            }
        }while (!jugador);
    }

}




