/*
 * CLASS MODEL 3 (CHILD CLASS)
 * INHERITANCE: PesertaTaekwondo mewarisi Peserta
 */
public class PesertaTaekwondo extends Peserta {
    private String kelasBerat;

    public PesertaTaekwondo(String nama, int umur, double beratBadan) {
        super(nama, umur, beratBadan);

        // CONDITION (if-else): kelas berat ditentukan otomatis
        if (beratBadan < 50) {
            kelasBerat = "Fin/Fly (< 50 kg)";
        } else if (beratBadan < 65) {
            kelasBerat = "Feather/Light (50 - 64 kg)";
        } else if (beratBadan < 80) {
            kelasBerat = "Middle (65 - 79 kg)";
        } else {
            kelasBerat = "Heavy (>= 80 kg)";
        }
    }

    // METHOD OVERRIDING
    @Override
    public String getCabang() {
        return "Taekwondo";
    }

    @Override
    public double hitungBiaya() {
        return 175000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kelas Berat     : " + kelasBerat);
    }

    public String getKelasBerat() {
        return kelasBerat;
    }
}
