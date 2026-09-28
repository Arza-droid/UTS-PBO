/*
 * CLASS MODEL 1 (PARENT CLASS)
 * Menyimpan data umum seluruh peserta pertandingan bela diri.
 */
public class Peserta {
    private static int counter = 0;      // untuk membuat nomor pendaftaran otomatis

    protected String noPendaftaran;
    protected String nama;
    protected int umur;
    protected double beratBadan;

    public Peserta(String nama, int umur, double beratBadan) {
        counter++;
        this.noPendaftaran = String.format("REG-%03d", counter);
        this.nama = nama;
        this.umur = umur;
        this.beratBadan = beratBadan;
    }

    // CONDITION (if-else): menentukan kategori usia
    public String getKategoriUsia() {
        if (umur < 12) {
            return "Anak-anak";
        } else if (umur < 15) {
            return "Pra-remaja";
        } else if (umur < 18) {
            return "Remaja";
        } else {
            return "Dewasa";
        }
    }

    public String getCabang() {
        return "Umum";
    }

    // METHOD OVERLOADING (nama sama, parameter berbeda)
    public double hitungBiaya() {
        return 100000;
    }

    public double hitungBiaya(double diskonPersen) {
        double biaya = hitungBiaya();
        return biaya - (biaya * diskonPersen / 100);
    }

    public void tampilkanInfo() {
        System.out.println("No. Pendaftaran : " + noPendaftaran);
        System.out.println("Nama            : " + nama);
        System.out.println("Umur            : " + umur + " tahun (" + getKategoriUsia() + ")");
        System.out.println("Berat Badan     : " + beratBadan + " kg");
        System.out.println("Cabang          : " + getCabang());
    }

    // Getter
    public String getNoPendaftaran() {
        return noPendaftaran;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public double getBeratBadan() {
        return beratBadan;
    }
}
