import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[][] player = new String[12][3];
    public static int bons = 0;
    public static int llops = 0;
    public static boolean matar=true;
    public static boolean revivir=true;


    public static void main(String[] args) {

        int menu = menu();
    }

    public static int menu() {
        Scanner input = new Scanner(System.in);

        int op = 0, vident;
        String mort;
        boolean end = false;
        int[] aleat=new int[2];
        String[] dead=new String[3];

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
                break;
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
                break;
            case 3:aleat=op3();
                do {
                    dead=nit12(aleat);
                    //Per a veure les morts que han hagut a la nit, una, dos o cap persones, i el seu respectiu rol
                    for (int x=0; x<12; x++){
                        if (Objects.equals(player[x][0], dead[0]) || Objects.equals(player[x][0], dead[1])) {
                            if (Objects.equals(player[x][0], dead[0]) && Objects.equals(player[x][2], "Mort")){
                                if (Objects.equals(dead[1], "Null")){
                                    System.out.print("El mort aquesta nit ha estat "+dead[0]);
                                    for (int j=0; j<12; j++){
                                        if (Objects.equals(player[j][0], dead[0])) {
                                            System.out.println(" que era " + player[j][1]+ " queden "+bons+" bons i "+llops+" llops");
                                        }
                                    }
                                } else {
                                    System.out.print("Aquesta nit han mort dues persones, primer "+dead[0]);
                                    for (int j=0; j<12; j++){
                                        if (Objects.equals(player[j][0], dead[0])) {
                                            System.out.print(" que era un " + player[j][1] + " i per últim " + dead[1] + " que era un ");
                                        }
                                    }
                                    for (int i=0; i<12; i++){
                                        if (Objects.equals(player[i][0], dead[1])){
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
                    //Per a veure si d'alguna forma es pot guanyar i canviem el factor determinant del while si acaba
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
                break;
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

                } else if (i==11){
                    System.out.println("El nom introduït no es troba, està malament escrit o ja està morta");
                }
            }
            for (int k=0; k<40;k++){
                System.out.println("\n");
            }
        }while (!jugador);

        jugador=false;


        return eliminat;
    }

    /**
     * Les accions del dia, només els vots de qui volen fotre fora
     */
    /**
     * El method que dona valors als llops i diu a tothom el seu rol i si es viu
     * @return el número de bruixa i vident que més tard es veurà si aquests estan vius i pòden jugar
     */
    public static int[] op3(){
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        int random1=0, random2=0, random3=0, random4=-2, random5=-2;
        int[] aleat=new int[2];

        //Llegim els noms dels participants de la partida i més endavant indiquem quants bons i quants llops n'hi ha a la partida
        for (int i=0; i<12; i++){
            System.out.println("Introdueix el nom del jugador "+i+": ");
            player[i][0]=input.next();
            player[i][2]="Viu";

        }

        bons=9;
        llops=3;

        //Donem valors randoms per a saber qui serà cada rol a la partida, fent que no puguin repetir, i més endavant els hi donem el seu valor
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

        //Donem a conèixer els rols dels jugadors
        for (int i=0; i<12; i++){
            System.out.println("El jugador "+player[i][0]+", quan estiguis sol escriu 'ok' per a continuar");
            String s=input.next();
            System.out.println("Ets un "+player[i][1]+"\nQuan ho tinguis introdueix algún paràmetre");
            s=input.next();
            for (int e=0; e<40; e++){
                System.out.println("\n");
            }
        }
        //Preparem l'array que tornarem amb els valors de la vident i la bruixa
        aleat[0]=random4;
        aleat[1]=random5;
        return aleat;
    }

    /**
     * La nit de la operació 3, amb les respectives activitats o jugades de cada rol
     * @param aleat valor posicional de a quina posició es troba la bruixa i la vident
     * @return retornem els noms de les persones eliminades en una array, i si nomes ha mort una es comprovara durant la ronda prèvia al dia veient si no es "NULL"
     */
    public static String[] nit12( int[] aleat){
        //Cridem l'scanner i paràmetres
        boolean jugador=false;
        String[] eliminat=new String[3];
        String rol;
        String useless;
        String resp="N";
        eliminat[1]="Null";

        Scanner input=new Scanner(System.in);
        System.out.println("Es fa de nit, i tant els llops, com la vident  com la bruixa tenen aproximadament un minut cadascú per escollir amb qui utilitzen el seu rol");

        //Comprovem que la vident estigui viva per fer la seva funcio, ensenyem els noms i llegim quin rol té aquella persona amb un control d'errors
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

        //Moment dels llops, amb una llista sense els seus noms i amb les persones vives, i aquests indiquen qui volen matar amb un control d'errors
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

        //Veiem si la bruixa està viva o si l'han matat els llops just en aquesta ronda, si es així entra, si és viva escull si reviure o matar sinó nomes reviu
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
            //L'acció de matar a algú nomes si esta viu mostrant la llista dels jugadors resultants
            if ( Objects.equals(player[aleat[1]][2], "Viu")) {
                if (matar && Objects.equals(resp, "N")) {
                    System.out.println("I vols eliminar a algun jugador? (S/N)");
                    resp = input.next();
                    if (Objects.equals(resp, "S")) {

                        do {
                            System.out.println("Els jugador disponibles son aquests: ");
                            for (int j = 0; j < 12; j++) {
                                if (Objects.equals(player[j][2], "Viu") && Objects.equals(player[j][1], "Vident") || Objects.equals(player[j][1], "Pueblerin") || Objects.equals(player[j][1], "Llop")) {
                                    System.out.println(player[j][0]);
                                }
                            }
                            System.out.println("Escriu a qui voleu eliminar: ");
                            eliminat[1] = input.next();
                            matar = false;

                            for (int i = 0; i < 12; i++) {
                                if (Objects.equals(eliminat[1], player[i][0]) && Objects.equals(player[i][2], "Viu")) {
                                    player[i][2] = "Mort";
                                    if (Objects.equals(player[i][1], "Llop")) {
                                        System.out.println("Era un llop");
                                        llops = llops - 1;
                                        jugador = true;
                                        break;
                                    } else if (Objects.equals(player[i][1], "Pueblerin")) {
                                        System.out.println("Era un pueblerin");
                                        bons = bons - 1;
                                        jugador = true;
                                        break;
                                    } else {
                                        System.out.println("Era la vident");
                                        bons = bons - 1;
                                        jugador = true;
                                        break;
                                    }


                                } else if (i == 11) {
                                    System.out.println("El nom introduït no es troba, està malament escrit o ja està morta");
                                }
                            }
                            for (int k = 0; k < 40; k++) {
                                System.out.println("\n");
                            }
                        } while (!jugador);
                    }
                }
            }

        }
        return eliminat;
    }

    /**
     * Les accions del dia, només els vots de qui volen fotre fora
     */
    public static void dia12(){
        //Valors, paràmetres que utilitzarem
        boolean jugador=false;
        String linchat, useless;
        Scanner input=new Scanner(System.in);

        //Mostrem jugadors vius i donem marge per a que entre ells decideixin a qui eliminar
        System.out.println("Ara que ja es de dia, es el torn de les discussions, preneu-vos el vostre temps");
        do{
            System.out.println("Aquí teniu una llista de qui queda viu");
            for (int j=0; j<12; j++){
                if (Objects.equals(player[j][2], "Viu")){
                    System.out.println(player[j][0]);
                }
            }
            // S'escull la persona que serà sacrificada, el que era i els jugadors que queden vius, tan bons, com llops
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
                    } else if (Objects.equals("Pueblerin ", player[i][1])){
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