import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[][] player=new String[12][3];
    public static int bons=0;
    public static int llops=0;
    public static boolean revivir=true;
    public static boolean matar=true;

    public static void main(String[] args) {

        int menu=menu();


    }

    /**
     * Aquest és el mètode per al cas recursiu
     * @return la opció del menú
     */
    public static int menu(){
        Scanner input=new Scanner(System.in);
        String[] mort=new String[3];
        boolean end = false;
        int[] aleat=new int[2];
        int op=0;

        System.out.println("Benvingut al joc del lobo de cachonegro\n" +
                "Seleccioneu una de les opcions disponibles per a jugar: \n" +
                "1. 6 jugadors (2 llops 4 pueblerins)\n2. 8 jugadors (2 llops 5 pueblerins 1 vident)\n" +
                "3. 12 jugadors (3 llops 6 pueblerins 1 vident 1 bruixa)\n4. Sortir ");
        op=input.nextInt();

        switch (op) {
            case 1:
            case 2:
            case 3:aleat=op3();
                do {
                    mort=nit12(aleat);
                    for (int x=0; x<12; x++){
                        if (Objects.equals(player[x][0], mort[0]) || Objects.equals(player[x][0], mort[1])) {
                            if (Objects.equals(player[x][0], mort[0]) && Objects.equals(player[x][2], "Mort")){
                                if (Objects.equals(mort[1], "Null")){
                                    System.out.print("El mort aquesta nit ha estat "+mort[0]);
                                    for (int j=0; j<12; j++){
                                        if (Objects.equals(player[j][0], mort[0])) {
                                            System.out.println(" que era " + player[j][1]+ " queden "+bons+" bons i "+llops+" llops");
                                        }
                                    }
                                } else {
                                    System.out.print("Aquesta nit han mort dues persones, primer "+mort[0]);
                                    for (int j=0; j<12; j++){
                                        if (Objects.equals(player[j][0], mort[0])) {
                                            System.out.print(" que era un " + player[j][1] + " i per últim " + mort[1] + " que era un ");
                                        }
                                    }
                                    for (int i=0; i<12; i++){
                                        if (Objects.equals(player[i][0], mort[1])){
                                            System.out.println(player[i][1]+" queden "+bons+" bons i "+llops+" llops");
                                        }
                                    }
                                }
                                break;
                            }
                        } else if (x==11){
                            System.out.println("El dia d'avui no ha mort ningú, queden "+bons+" bons i "+llops+" llops");
                            break;
                        }
                    }

                    String useless=input.next();
                    dia12();
                    if (bons==llops || bons<llops){
                        System.out.println("Victòria dels llops, enhorabona, aaauuuuu");
                        end=true;
                        break;

                    } else if (llops==0){
                        System.out.println("Victòria dels pueblerins, enhorabona!!");
                        end=true;

                    } else {
                        System.out.println("Torna la nit, tothom a dormir");
                    }
                }while(!end);

            case 4:
                break;
            default:
                System.out.println("ERROR: Opció no vàlida!");
                menu();
                break;
        }
        return op;
    }
    public static int[] op3(){
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        int random1=0, random2=0, random3=0, random4=-2, random5=-2;
        int[] aleat=new int[2];


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
        do{
            random5 = random.nextInt(12);
        }while (random5==random1 || random5==random2 || random5==random3 || random5<0 || random5==random4);

        for (int i=0; i<12; i++){
            if (i==random1 || i==random2 || i==random3){
                player[i][1]="Llop";
            } else if (i==random4) {
                player[i][1]="Vident";
            } else if (i==random5){
                player[i][1]="Bruixa";
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
        aleat[0]=random4;
        aleat[1]=random5;
        return aleat;
    }
    public static String[] nit12( int[] aleat){
        boolean jugador=false;
        String[] eliminat=new String[3];
        String rol;
        String useless;
        String resp="N";
        eliminat[1]="Null";

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, i tant els llops, com la vident  com la bruixa tenen aproximadament un minut cadascú per escollir amb qui utilitzen el seu rol");


        if (Objects.equals(player[aleat[0]][2], "Viu")) {
            System.out.println("Quan la vident estigui llesta que introudeixi algun parametre");
            useless=input.next();
            do {


                System.out.println("Torn de la vident, aqui tenim una llista dels noms: ");
                for (int j = 0; j < 12; j++) {
                    if (Objects.equals(player[j][2], "Viu") && Objects.equals(player[j][1], "Pueblerin")    || Objects.equals(player[j][1], "Llop") || Objects.equals(player[j][1], "Bruixa")) {
                        System.out.println(player[j][0]);
                    }
                }
                System.out.println("De qui vols saber el seu rol? ");
                rol = input.next();
                for (int i = 0; i < 12; i++) {
                    if (Objects.equals(rol, player[i][0])) {
                        System.out.println(player[i][1]);
                        System.out.println("Quan ho tinguis introdueix algun parametre");
                        useless = input.next();
                        jugador = true;
                        break;
                    } else if (i == 11) {
                        System.out.println("El nom introduït no es troba o està malament escrit");
                    }
                }
            } while (!jugador);
        }
        jugador=false;
        for (int k=0; k<40;k++){
            System.out.println("\n");
        }

        do{
            System.out.println("Moment de despertar els llops, un cop estiguin preparats introduïu cualsevol paràmetre");
            useless=input.next();
            System.out.println("Els jugador disponibles son aquests: ");
            for (int j=0; j<12; j++){
                if (Objects.equals(player[j][2], "Viu") && Objects.equals(player[j][1], "Pueblerin")    || Objects.equals(player[j][1], "Vident") || Objects.equals(player[j][1], "Bruixa")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu a qui voleu eliminar: ");
            eliminat[0]=input.next();

            for (int i=0; i<12; i++){
                if (Objects.equals(eliminat[0], player[i][0]) && Objects.equals(player[i][2], "Viu")){
                    player[i][2]="Mort";
                    bons=bons-1;
                    jugador=true;
                    break;
                } else if (i==11){
                    System.out.println("El nom introduït no es troba, està malament escrit o ja està morta");
                }
            }
            for (int k=0; k<40;k++){
                System.out.println("\n");
            }
        }while (!jugador);

        jugador=false;
        if ( Objects.equals(player[aleat[1]][0], eliminat[0]) || Objects.equals(player[aleat[1]][2], "Viu") ){
            System.out.println("Per últim es desperta la bruixa, quan estigui llesta que introdueixi algun paràmetre");
            useless=input.next();

            if (revivir) {
                System.out.println("El mort ha estat " + eliminat[0] + " vols revivir-lo? (S/N): ");
                resp=input.next();
                if (Objects.equals(resp, "S")){
                    for (int i=0; i<12; i++){
                        if (Objects.equals(eliminat[0], player[i][0])) {
                            player[i][2] = "Viu";
                            bons = bons + 1;
                            break;
                        }
                    }
                    revivir=false;
                    System.out.println("Reviscut amb exit");
                }
            }

            if (matar && Objects.equals(resp, "N")) {
                System.out.println("I vols eliminar a algun jugador? (S/N)");
                resp=input.next();
                if (Objects.equals(resp, "S")){

                    do{
                        System.out.println("Els jugador disponibles son aquests: ");
                        for (int j=0; j<12; j++){
                            if (Objects.equals(player[j][2], "Viu") && Objects.equals(player[j][1], "Vident") || Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Llop") ){
                                System.out.println(player[j][0]);
                            }
                        }
                        System.out.println("Escriu a qui voleu eliminar: ");
                        eliminat[1]=input.next();
                        matar=false;

                        for (int i=0; i<12; i++){
                            if (Objects.equals(eliminat[1], player[i][0]) && Objects.equals(player[i][2], "Viu")){
                                player[i][2]="Mort";
                                if (Objects.equals(player[i][1], "Llop")){
                                    System.out.println("Era un llop");
                                    llops=llops-1;
                                    jugador=true;
                                    break;
                                } else if (Objects.equals(player[i][1],"Pueblerin")) {
                                    System.out.println("Era un pueblerin");
                                    bons=bons-1;
                                    jugador=true;
                                    break;
                                } else {
                                    System.out.println("Era la vident");
                                    bons=bons-1;
                                    jugador=true;
                                    break;
                                }



                            } else if (i==11){
                                System.out.println("El nom introduït no es troba, està malament escrit o ja està morta");
                            }
                        }
                        for (int k=0; k<40;k++){
                            System.out.println("\n");
                        }
                    }while (!jugador);
                }
            }

        }
        return eliminat;
    }
    public static void dia12(){
        boolean jugador=false;
        String linchat, useless;
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
                        System.out.println("El jugador "+player[i][0]+" era un llop! Ja només queden "+llops+" llops");
                        useless=input.next();
                    } else if (Objects.equals("Vident", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era la vident! Ara queden "+bons+" bons");
                        useless=input.next();
                    } else if (Objects.equals("Pueblerin", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era un pueblerin! Ara queden "+bons+" bons");
                        useless=input.next();
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