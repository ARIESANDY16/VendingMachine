public class Makanan extends Transaction{
    private int amount;
    private Keypad keypad;
    private CekStok CekStok;
    private final static int CANCELED = 6;
    int input;

    public Makanan(int saldoNumber, Screen usrScreen, Database_Saldo atmBankDatabase, Keypad usrKeypad, CekStok stokmakanan){
        super(saldoNumber, usrScreen, atmBankDatabase);

        keypad = usrKeypad;
        CekStok = stokmakanan;
    }

    @Override
    public void execute(){

        boolean makananKeluar = false;
        double saldo;
        Database_Saldo bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        do{
            amount = displayMenuOfAmounts();

            if(amount != CANCELED){
                saldo = Database_Saldo.getSaldo(getNominalNumber());

                if(amount <= saldo){
                    if(CekStok.stokCukup(input)){

                        Database_Saldo.beli(getNominalNumber(), amount);

                        CekStok.keluarkanMakanan(input);
                        makananKeluar = true;
                        screen.displayMessageLine("\nTransaksi sukses. Silahkan Ambil makanan anda.");
                    } else{
                        screen.displayMessageLine("\nMaaf, makanan pilihan anda habis.");
                    }
                } else{
                    screen.displayMessageLine("\nMaaf, saldo anda tidak cukup.");
                }
            }
            else{
                screen.displayMessageLine("\nMembatalkan Transaksi...");
                return;
            }
        }   while(!makananKeluar);
    }

    private int displayMenuOfAmounts(){
        int harga = 0;
        Screen screen = getScreen();
        int[] amounts = {0, 6000, 8000, 10000, 12000, 15000};

        while(harga == 0){
            screen.displayMessageLine("\n\nDaftar Makanan : ");
            screen.displayMessageLine("1 - Biskuit Rp 6000,00");
            screen.displayMessageLine("2 - Chips Rp 8000,00");
            screen.displayMessageLine("3 - Oreo Rp 10000,00");
            screen.displayMessageLine("4 - Tango Rp 12000,00");
            screen.displayMessageLine("5 - Cokelat Rp 15000,00");
            screen.displayMessageLine("6 - Batalkan Pembelian");
            screen.displayMessage("\nPilih Makanan : ");

            input = keypad.getInput();

            switch(input){
                case 1 :
                case 2 :
                case 3 :
                case 4 :
                case 5 :
                    harga = amounts[input];
                    break;

                case 6 :
                    harga = CANCELED;
                    break;

                default :
                    screen.displayMessageLine("\nInput Salah");
                    screen.displayMessageLine("Silahkan Coba lagi");
            }
        }
        return harga;
    }
}
