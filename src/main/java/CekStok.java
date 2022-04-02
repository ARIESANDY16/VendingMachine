public class CekStok {
    private int count[]={0, 3, 3, 3, 3, 3};

    public void keluarkanMakanan(int pilihan){
        count[pilihan] -= 1;
    }

    public boolean stokCukup(int pilihan){
        if ( count[pilihan] > 0 )
            return true;
        else
            return false;
    }
}
