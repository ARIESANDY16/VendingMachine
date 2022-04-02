public class Nominal {
    private double sisasaldo;
    private int uangAnda;

    public Nominal( int theUangNumber, double saldo)
    {
        uangAnda = theUangNumber;
        sisasaldo = saldo;
    }

    public boolean validateUang(String nominal){
        if(nominal.equals("2000") || nominal.equals("5000") || nominal.equals("10000") || nominal.equals("20000") || nominal.equals("50000")){
            return true;
        } else {
            return false;
        }
    }

    public double getSaldo(){
        return sisasaldo;
    }

    public void beli( double amount ){
        sisasaldo -= amount;
    }

    public int getUangNumber(){
        return uangAnda;
    }

}
