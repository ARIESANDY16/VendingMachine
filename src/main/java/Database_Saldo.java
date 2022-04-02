public class Database_Saldo {
    private static Nominal[] nominals;

    public Database_Saldo(){
        nominals = new Nominal[ 6 ];
        nominals[ 0 ] = new Nominal( 2000, 2000.0);
        nominals[ 1 ] = new Nominal( 5000, 5000.0);
        nominals[ 2 ] = new Nominal( 10000, 10000.0);
        nominals[ 3 ] = new Nominal( 20000, 20000.0);
        nominals[ 4 ] = new Nominal( 50000, 50000.0);
        nominals[ 5 ] = new Nominal( 100000, 100000.0);
    }

    public static Nominal getNominal( int uangNumber ){
        for ( Nominal currentNominal : nominals )
        {
            if ( currentNominal.getUangNumber() == uangNumber )
                return currentNominal;
        }
        return null;
    }

    public boolean syncSaldo( int uangNumber){
        Nominal nominalSaldo = getNominal(uangNumber);
        if ( nominalSaldo != null )
            return nominalSaldo.validateUang( String.valueOf(uangNumber));
        else
            return false;
    }

    public static double getSaldo( int saldoNumber ){
        return getNominal( saldoNumber ).getSaldo();
    }

    public static void beli( int saldoNumber, double amount ){
        getNominal( saldoNumber ).beli( amount );
    }
}
