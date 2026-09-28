/*
 * CLASS MODEL 2 (CHILD CLASS)
 * INHERITANCE: PesertaKarate mewarisi Peserta
 */
public class PesertaKarate extends Peserta {
    private String sabuk;

    public PesertaKarate(String nama, int umur, double beratBadan, String sabuk) {
        super(nama, umur, beratBadan);
        this.sabuk = sabuk;
    }

    // METHOD OVERRIDING
    @Override
    public String getCabang() {
        return "Karate";
    }

    // METHOD OVERRIDING: biaya lebih mahal untuk sabuk hitam (CONDITION)
    @Override
    public double hitungBiaya() {
        if (sabuk.equalsIgnoreCase("hitam")) {
            return 200000;
        } else {
            return 150000;
        }
    }

    // METHOD OVERRIDING: menambahkan info sabuk
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Sabuk           : " + sabuk);
    }

    public String getSabuk() {
        return sabuk;
    }
}
