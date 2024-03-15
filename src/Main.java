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

        int op = 0, vident;
        String mort;
        boolean end = false;

        //opcions de joc (jo 1 i 2)

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
            case 2:vident=op2();
                do {
                    mort=nitop2(vident);
                    System.out.println("El mort aquesta nit ha estat "+mort+", queden "+bons+" bons i "+llops+"llops");

                    diaop2();

                    if (bons==llops || bons<llops){
                        System.out.println("Victòria dels llops, enhorabona, aaauuuuu");
                        end=true;
                    } else if (llops==0){
                        System.out.println("Victòria dels pueblerins, enhorabona!!");
                        end=true;
                    } else {
                        System.out.println("Torna la nit, tothom a dormir");
                    }
                }while(!end);
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

    /**
     * Aquesta és la opció 1 en la cual participen 6 persones, 4 poblerins i 2 llops.
     */
    public static void op1() {
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

        //repartició de llops i poblerins
        for (int i=0; i<6; i++){
            if (i==random1 || i==random2){
                player[i][1]="Llop";
            } else {
                player[i][1]="Pueblerin";
            }
        }

        //saber els rols anteriorment repartits
        for (int i=0; i<6; i++){
            System.out.println(" Jugador "+player[i][0]+", quan no et vegi ningú escriu ok per saber el teu rol");
            String ok=input.next();
            System.out.println("Ets un "+player[i][1]);
            System.out.println("Escriu ok per pasar al seguent jugador");
            String okk=input.next();

            //deixar espai perue no pugin mirar els rols dels altres
            for (int j=0; j<15; j++){
                System.out.println("\n");
            }
        }
    }

    /**
     * Aquesta és la opció 2 la qual participen 8 persones, 5 poblerins, 2 llops i
     * aqui entra el rol de la vident, la cual te el poder de cada nit, pot escull a una
     * persona per saber el seu rol.
     */
    public static int op2() {

        // inicialització de scanners randoms i variables

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int random1 = 0, random2 = 0, random3 = 0;
        for (int i = 0; i < 8; i++) {
            System.out.println("Introdueix el nom del jugador " + i + ": ");
            player[i][0] = input.next();
            player[i][1] = "0";
            player[i][2]="Viu";
        }
        bons=6;
        llops=2;

        // Triar llops, vident i poblerins
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

        //repartició dels rols anteriorment asignats

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
        return random3;
    }

    /**
     * Aquesta es la nit 1 de la opcio 1, basicament aqui és com que ja estan asignats
     * tots els rols, la gent dorm i els llops durant cada nit ahuran de matar a un pobleri
     * @return
     */
    public static String nitop1(){
        boolean jugador=false;
        String eliminat, rol;

        // Es mostra una llista de las persones que hi han vives

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, els llops tindran un minut per escollir a qui matar");
        for (int j = 0; j < 6; j++) {
            if (Objects.equals(player[j][1], "Pueblerin")  && Objects.equals(player[j][2], "Viu")) {
                System.out.println(player[j][0]);
            }
        }
        // aquí s'introdueix el nom de la persona que volen matar, amb un control de errors si la persona no coincideix amb els noms anteriorment introduits i es guarden en la variable eliminats
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

    /**
     * Basicament el que es fa en el dia, es que tan els llops con els poblerins discuteixen
     * per intentar encertar qui son els llops i s'introdueix el nom de la persona mes
     * votada
     */
    public static void diaop1(){
        boolean jugador=false;
        String linchat, okkkk;
        Scanner input=new Scanner(System.in);

        // aquí es fa de dia i han de decidir entre tots qui creuen que son llops(els llops
        //s'han de defendre i fer creure als poblerins que no son ells)

        System.out.println("Ara que ja es de dia, es el torn de les discussions, preneu-vos el vostre temps");
        do{
            System.out.println("Aquí teniu una llista de qui queda viu");
            for (int j=0; j<6; j++){
                if (Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu el nom de la persona que creieu que es llop: ");
            linchat=input.next();
            for (int i=0; i<6; i++){
                if (Objects.equals(linchat, player[i][0])){
                    player[i][2]="Mort";
                    jugador=true;
                    if (Objects.equals("Llop", player[i][1])){
                        llops=llops-1;
                        System.out.println("El jugador "+player[i][0]+" era llop, Ja només queden "+llops+" llop");
                        okkkk=input.next();
                    } else if (Objects.equals("Pueblerin", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era pueblerin, Ara queden "+bons+" bons");
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


    /**
     * dia opció 2 es casi igual que el de la opció 1, pero el que cambia es que participa
     * la vident, llavors es casi tot igual, pero amb algun petit cambi.
     */
    public static void diaop2(){
        boolean jugador=false;
        String linchat, okkkk;
        Scanner input=new Scanner(System.in);

        System.out.println("Ara que ja es de dia, es el torn de les discussions, preneu-vos el vostre temps");
        do{
            System.out.println("Aquí teniu una llista de qui queda viu");
            for (int j=0; j<8; j++){
                if (Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Escriviu el nom de la persona que creieu que es llop: ");
            linchat=input.next();
            for (int i=0; i<8; i++){
                if (Objects.equals(linchat, player[i][0])){
                    player[i][2]="Mort";
                    jugador=true;
                    if (Objects.equals("Llop", player[i][1])){
                        llops=llops-1;
                        System.out.println("El jugador "+player[i][0]+" era llop, Ja només queden "+llops+" Llops");
                        okkkk=input.next();
                    } else if (Objects.equals("Vident", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era vident, Ara queden "+bons+" bons");
                        okkkk=input.next();
                    } else if (Objects.equals("Pueblerin", player[i][1])){
                        bons=bons-1;
                        System.out.println("El jugador "+player[i][0]+" era pueblerin, Ara queden "+bons+" bons");
                        okkkk=input.next();
                    }
                    break;
                } else if (i==7){
                    System.out.println("El nom introduit està malament escrit");
                }
            }
            for (int k=0; k<25;k++){
                System.out.println("\n");
            }
        }while (!jugador);
    }

    /**
     * la nit de la opció 2 també es molt semblan a la de la opció 1, pero en aquesta tenim un
     * nou rol que és la vident, llavors s'ha tincgut que picar més codi ja que la vident
     * ha de saber els rols dels altres.
     *
     * @return
     */
    public static String nitop2(int vident){
        boolean jugador=false;
        String eliminat, rol;

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, i tant els llops com la vident tenen aproximadament un minut cadascú per escollir amb qui utilitzen el seu rol");
        System.out.println("Quan la vident estigui llesta que introudeixi algun parametre");
        String useless=input.next();

        // i aquí tenim la part de la vident, per que se li imprimeixi la gent que queda viva i de aquella gent saber un rol per ronda.
        if (Objects.equals(player[vident][2], "Viu")) {
        do {

                System.out.println("Torn de la vident, aqui tenim una llista dels noms: ");
                for (int j = 0; j < 8; j++) {
                    if (Objects.equals(player[j][1], "Pueblerin") && Objects.equals(player[j][2], "Viu") || Objects.equals(player[j][1], "Llop") && Objects.equals(player[j][2], "Viu")) {
                        System.out.println(player[j][0]);

                    }
                }
                System.out.println("De qui vols saber el seu rol? ");
                rol = input.next();
                for (int i = 0; i < 8; i++) {
                    if (Objects.equals(rol, player[i][0])) {
                        System.out.println(player[i][1]);
                        System.out.println("Quan ho tinguis introdueix algun parametre");
                        useless = input.next();
                        jugador = true;
                        break;
                    } else if (i == 7) {
                        System.out.println("El nom introduït no es troba o està malament escrit");
                    }
                }

            }
            while (!jugador) ;
        }
        jugador=false;
        for (int k=0; k<25;k++){
            System.out.println("\n");
        }

        do{
            System.out.println("Els jugador disponibles son aquests: ");
            for (int j=0; j<8; j++){
                if (Objects.equals(player[j][1], "Pueblerin")  && Objects.equals(player[j][2], "Viu") || Objects.equals(player[j][1], "Vident") && Objects.equals(player[j][2], "Viu"))
                {
                    System.out.println(player[j][0]);
                }
            }
            System.out.println("Llops, Escriviu a qui voleu eliminar: ");
            eliminat=input.next();

            for (int i=0; i<8; i++){
                if (Objects.equals(eliminat, player[i][0])){
                    player[i][2]="Mort";
                    bons=bons-1;
                    jugador=true;
                    break;
                } else if (i==7){
                    System.out.println("El nom introduït no es troba o està malament escrit");
                }
            }
            for (int k=0; k<25;k++){
                System.out.println("\n");
            }
        }while (!jugador);
        return eliminat;
    }
}





