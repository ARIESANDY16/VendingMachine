public abstract class Transaction {
    private int nominalNumber;
    private Screen screen;
    private Database_Saldo Database;

    public Transaction( int saldoNumber, Screen usrScreen, Database_Saldo BankDatabase){
        nominalNumber = saldoNumber;
        screen = usrScreen;
        Database = BankDatabase;
    }

    public int getNominalNumber(){
        return nominalNumber;
    }

    public Screen getScreen(){
        return screen;
    }

    public Database_Saldo getBankDatabase(){
        return Database;
    }

    abstract public void execute();
}
