public class VendingMachine {
    private boolean cheking;
    private int currentUangNumber;
    private Screen screen;
    private Keypad keypad;
    private CekStok CekStok;
    private Database_Saldo bankDatabase;

    private static final int CekSaldo = 2;
    private static final int MengambilMakanan = 1;

    public VendingMachine(){
        cheking = false;
        currentUangNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        CekStok = new CekStok();
        bankDatabase = new Database_Saldo();
    }

    public void run(){
        while(true){
            while(!cheking){
                screen.displayMessageLine("Selamat Datang di Vending Machine!\nBeli makanan ringan untuk anak anda hehe!");
                RequestInquiry();
            }

            performTransactions();
            cheking = false;
            currentUangNumber = 0;
            screen.displayMessageLine("\nTerima kasih!\nSilahkan kembali lagi!\n\n");
        }
    }

    private void RequestInquiry(){
        screen.displayMessage("\nMasukkan Uang Anda pecahan nominal : 2000, 5000, 10000, 20000, 50000 !!!");
        int uangNumber = keypad.getInput();

        cheking = bankDatabase.syncSaldo(uangNumber);

        if(cheking){
            currentUangNumber = uangNumber;
        }
        else{
            screen.displayMessageLine("\nHarap masukan uang dengan nominal ketentuan");
            screen.displayMessageLine("Silahkan coba lagi.\n");
            RequestInquiry();
        }
    }

    private void performTransactions(){
        int a=0;
        Transaction currentTransaction = null;
        boolean userExited = false;

        currentTransaction = createTransaction(1);
        currentTransaction.execute();

        currentTransaction = createTransaction(2);
        currentTransaction.execute();

        while(!userExited){

            int mainMenuSelection = displayMainMenu();
            switch(mainMenuSelection){
                case 1 :
                    currentTransaction = createTransaction(1);
                    currentTransaction.execute();

                    currentTransaction = createTransaction(2);
                    currentTransaction.execute();
                    break;

                case 2 :
                    screen.displayMessageLine("\nKeluar...");
                    screen.displayMessage("\nSilahkan ambil kembalian uang anda");
                    userExited = true;
                    break;

                default :
                    screen.displayMessageLine("\nInput Salah.");
                    screen.displayMessageLine("Silahkan Coba lagi.");
                    break;
            }
        }
    }

    private int displayMainMenu(){
        screen.displayMessageLine("\n\nLanjut Pembelian? :");
        screen.displayMessageLine("1. Lanjut");
        screen.displayMessageLine("2. Keluar");
        screen.displayMessage("Pilihan : ");
        return keypad.getInput();
    }

    private Transaction createTransaction(int type){
        Transaction temp = null;

        switch(type){
            case MengambilMakanan :

                temp = new Makanan(currentUangNumber, screen, bankDatabase, keypad, CekStok);
                break;

            case CekSaldo :

                temp = new Saldo(currentUangNumber, screen, bankDatabase);
                break;
        }
        return temp;
    }
}
