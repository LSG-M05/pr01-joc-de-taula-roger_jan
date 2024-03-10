import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[][] player = new String[6][2];
    public static int bons = 0;
    public static int llops = 0;


    public static void main(String[] args) {

        int menu = menu();


    }

    public static int menu() {
        Scanner input = new Scanner(System.in);

        int op = 0;

        System.out.println("Benvingut al joc del lobo de cachonegro\n" +
                "Seleccioneu una de les opcions disponibles per a jugar: \n" +
                "1. 6 jugadors (2 llops 4 pueblerins)\n 2. 8 jugadors (2 llops 5 pueblerins 1 vident)\n" +
                "3. 12 jugadors (3 llops 7 pueblerins 1 bruixa 1 vident)\n 4. Sortir ");
        op = input.nextInt();

        switch (op) {
            case 1:
                op1();
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

    public static void op1() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int random1 = 0, random2 = 0, random3 = 0;
        for (int i = 0; i < 6; i++) {
            System.out.println("Introdueix el nom del jugador " + i + ": ");
            player[i][0] = input.next();
            player[i][1] = "0";
        }



        do {
            random1 = random.nextInt(6);
            random2 = random.nextInt(6);
        }while (random1==random2);

        do{
            random3 = random.nextInt(6);
        }while (random3==random1 || random3==random2);
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

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();


        }
    }

}