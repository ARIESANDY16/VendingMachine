public class Screen {
    public void displayMessage(String message){
        System.out.println(message);
    }
    public void displayMessageLine(String message){
        System.out.println(message);
    }
    public void displaySaldo( double amount ){
        System.out.printf( "Rp %,.2f", amount );
    }
}
