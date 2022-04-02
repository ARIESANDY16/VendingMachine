public class Saldo extends Transaction{
    public Saldo( int userAccountNumber, Screen vendingScreen, Database_Saldo vendingBankDatabase ){
        super( userAccountNumber, vendingScreen, vendingBankDatabase );
    }

    @Override
    public void execute(){
        Database_Saldo bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        double saldo = bankDatabase.getSaldo( getNominalNumber());
        screen.displayMessageLine( "\nSaldo Anda : " );
        screen.displaySaldo( saldo );
    }
}
