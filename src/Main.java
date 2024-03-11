import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[][] player=new String[12][3];
    public static int bons=0;
    public static int llops=0;

    public static void main(String[] args) {

       int menu=menu();


    }

    /**
     * Aquest és el mètode per al cas recursiu
     * @return la opció del menú
     */
    public static int menu(){
        Scanner input=new Scanner(System.in);
        String mort;
        int op=0;

            System.out.println("Benvingut al joc del lobo de cachonegro\n" +
                    "Seleccioneu una de les opcions disponibles per a jugar: \n" +
                    "1. 6 jugadors (2 llops 4 pueblerins)\n2. 8 jugadors (2 llops 5 pueblerins 1 vident)\n" +
                    "3. 12 jugadors (3 llops 7 pueblerins 1 vident)\n4. Sortir ");
            op=input.nextInt();

        switch (op) {
            case 1:
            case 2:
            case 3:op3();
                do {
                    mort=nit();
                    System.out.println("El mort aquesta nit ha estat "+mort+", queden "+bons+" bons i "+llops+"llops");
                    dia();
                }while(bons>llops || llops!=0);
            case 4:
                break;
            default:
                System.out.println("ERROR: Opció no vàlida!");
                menu();
                break;
        }
        return op;
    }
    public static void op3(){
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        int random1=0, random2=0, random3=0, random4=-2;


        for (int i=0; i<12; i++){
            System.out.println("Introdueix el nom del jugador "+i+": (L'estil ha de ser 'Francesc')");
            player[i][0]=input.next();
            player[i][2]="Viu";

        }
        bons=9;
        llops=3;

        do {
            random1 = random.nextInt(12);
            random2 = random.nextInt(12);
            random3 = random.nextInt(12);
        }while (random1==random2 || random1==random3 || random2==random3);
        do{
            random4 = random.nextInt(12);
        }while (random4==random1 || random4==random2 || random4==random3 || random4<0);
        for (int i=0; i<12; i++){
            if (i==random1 || i==random2 || i==random3){
                player[i][1]="Llop";
            } else if (i==random4) {
                player[i][1]="Vident";
            } else {
                player[i][1]="Pueblerin";
            }
        }


        for (int i=0; i<12; i++){
            System.out.println("El jugador "+player[i][0]+", quan estiguis sol escriu 'ok' per a continuar");
            String s=input.next();
            System.out.println("Ets un "+player[i][1]+"\nQuan ho tinguis introdueix algún paràmetre");
            s=input.next();
            for (int e=0; e<40; e++){
                System.out.println("\n");
            }
        }

    }
    public static String nit(){
        boolean jugador=false;
        String eliminat, rol;

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, i tant els llops com la vident tenen aproximadament un minut cadascú per escollir amb qui utilitzen el seu rol");
        System.out.println("Quan la vident estigui llesta que introudeixi algun parametre");
        String useless=input.next();

        do {
            System.out.println("Torn de la vident, aqui tenim una llista dels noms: ");
            for (int j = 0; j < 12; j++) {
                if (Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Llop") && Objects.equals(player[j][2], "Viu")) {
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("De qui vols saber el seu rol? ");
            rol=input.next();
            for (int i=0; i<12; i++){
                if (Objects.equals(rol, player[i][0])){
                    System.out.println(player[i][1]);
                    System.out.println("Quan ho tinguis introdueix algun parametre");
                    useless=input.next();
                    jugador=true;
                    break;
                } else if (i==11){
                    System.out.println("El nom introduït no es troba o està malament escrit");
                }
            }
        }while(!jugador);
        jugador=false;
        for (int k=0; k<40;k++){
            System.out.println("\n");
        }

        do{
            System.out.println("Els jugador disponibles son aquests: ");
            for (int j=0; j<12; j++){
                if (Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Vident") && Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu a qui voleu eliminar: ");
            eliminat=input.next();

            for (int i=0; i<12; i++){
                if (Objects.equals(eliminat, player[i][0])){
                    player[i][2]="Mort";
                    bons=bons-1;
                    jugador=true;
                    break;
                } else if (i==11){
                    System.out.println("El nom introduït no es troba o està malament escrit");
                }
            }
            for (int k=0; k<40;k++){
                System.out.println("\n");
            }
        }while (!jugador);
        return eliminat;
    }
    public static void dia(){
        boolean jugador=false;
        String linchat;
        Scanner input=new Scanner(System.in);

        System.out.println("Ara que ja es de dia, es el torn de les discussions, preneu-vos el vostre temps");
        do{
            System.out.println("Aquí teniu una llista de qui queda viu");
            for (int j=0; j<12; j++){
                if (Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu el nom de la persona que serà sacrificada: ");
            linchat=input.next();

            for (int i=0; i<12; i++){
                if (Objects.equals(linchat, player[i][0])){
                    player[i][2]="Mort";
                    jugador=true;
                    if (Objects.equals("Llop", player[i][1])){
                        llops=llops-1;
                        System.out.println("El jugador "+player[i][0]+" era un llop! Ja només queden "+llops);
                    } else if (Objects.equals("Vident", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era la vident! Ara queden "+bons);
                    } else if (Objects.equals("Pueblerin", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era un pueblerin! Ara queden "+bons);
                    }
                    break;
                } else if (i==11){
                    System.out.println("El nom introduït no es troba o està malament escrit");
                }
            }
            for (int k=0; k<40;k++){
                System.out.println("\n");
            }
        }while (!jugador);
    }

}