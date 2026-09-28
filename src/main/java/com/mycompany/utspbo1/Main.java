import java.util.ArrayList;
import java.util.Scanner;

/*
 * SISTEM PENDATAAN PENDAFTARAN PERTANDINGAN BELA DIRI
 * Tugas UTS Pemrograman Berorientasi Objek (Java)
 *
 * Struktur file:
 *  - Main.java              : program utama (menu, input, looping)
 *  - Peserta.java           : parent class
 *  - PesertaKarate.java     : child class (inheritance)
 *  - PesertaTaekwondo.java  : child class (inheritance)
 */
public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Peserta> daftarPeserta = new ArrayList<>();
    static final String NAMA_EVENT = "Kejuaraan Bela Diri Antar Dojo 2026";

    // ===================== MAIN =====================
    public static void main(String[] args) {
        int pilihan;

        // LOOPING utama menu
        do {
            System.out.println("\n==========================================");
            System.out.println(" " + NAMA_EVENT);
            System.out.println("==========================================");
            System.out.println("1. Daftar Peserta Baru");
            System.out.println("2. Lihat Semua Peserta");
            System.out.println("3. Cari Peserta");
            System.out.println("4. Rekap Pendapatan");
            System.out.println("0. Keluar");
            pilihan = bacaAngka("Pilih menu: ");

            // CONDITION (if-else)
            if (pilihan == 1) {
                daftarBaru();
            } else if (pilihan == 2) {
                tampilkanSemua();
            } else if (pilihan == 3) {
                System.out.print("Masukkan nama yang dicari: ");
                cariPeserta(input.nextLine());
            } else if (pilihan == 4) {
                rekapPendapatan();
            } else if (pilihan == 0) {
                System.out.println("\nTerima kasih! Program selesai.");
            } else {
                System.out.println("Menu tidak tersedia!");
            }
        } while (pilihan != 0);
    }

    // ===================== INPUT HELPER =====================
    static int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    static double bacaDesimal(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    // ===================== FITUR PROGRAM =====================
    static void daftarBaru() {
        System.out.println("\n--- Pilih Cabang Bela Diri ---");
        System.out.println("1. Karate");
        System.out.println("2. Taekwondo");
        int cabang = bacaAngka("Pilih cabang: ");

        if (cabang != 1 && cabang != 2) {
            System.out.println("Cabang tidak valid!");
            return;
        }

        System.out.print("Nama lengkap     : ");
        String nama = input.nextLine();
        int umur = bacaAngka("Umur             : ");
        double berat = bacaDesimal("Berat badan (kg) : ");

        // Validasi dengan if-else
        if (umur < 6 || umur > 60) {
            System.out.println("Pendaftaran ditolak! Umur peserta harus antara 6 - 60 tahun.");
            return;
        }
        if (berat <= 0) {
            System.out.println("Pendaftaran ditolak! Berat badan tidak valid.");
            return;
        }

        Peserta p;
        if (cabang == 1) {
            System.out.print("Sabuk (putih/kuning/hijau/biru/coklat/hitam): ");
            String sabuk = input.nextLine();
            p = new PesertaKarate(nama, umur, berat, sabuk);
        } else {
            p = new PesertaTaekwondo(nama, umur, berat);
        }

        daftarPeserta.add(p);
        System.out.println("\n>> Pendaftaran berhasil! No. Pendaftaran: " + p.getNoPendaftaran());
    }

    static void tampilkanSemua() {
        if (daftarPeserta.isEmpty()) {
            System.out.println("\nBelum ada peserta yang terdaftar.");
            return;
        }

        System.out.println("\n===== DAFTAR PESERTA (" + daftarPeserta.size() + " orang) =====");
        // LOOPING for biasa
        for (int i = 0; i < daftarPeserta.size(); i++) {
            System.out.println("--- Peserta ke-" + (i + 1) + " ---");
            Peserta p = daftarPeserta.get(i);

            // POLYMORPHISM: method yang dijalankan sesuai tipe objek sebenarnya
            p.tampilkanInfo();

            // Peserta di bawah 12 tahun diskon 10% (memakai OVERLOADING)
            if (p.getUmur() < 12) {
                System.out.println("Biaya Daftar    : Rp" + (long) p.hitungBiaya(10) + " (diskon 10%)");
            } else {
                System.out.println("Biaya Daftar    : Rp" + (long) p.hitungBiaya());
            }
            System.out.println();
        }
    }

    static void cariPeserta(String kataKunci) {
        boolean ditemukan = false;

        // LOOPING for-each
        for (Peserta p : daftarPeserta) {
            if (p.getNama().toLowerCase().contains(kataKunci.toLowerCase())) {
                System.out.println();
                p.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("\nPeserta dengan nama \"" + kataKunci + "\" tidak ditemukan.");
        }
    }

    static void rekapPendapatan() {
        double total = 0;
        int karate = 0, taekwondo = 0;

        for (Peserta p : daftarPeserta) {
            if (p.getUmur() < 12) {
                total += p.hitungBiaya(10);
            } else {
                total += p.hitungBiaya();
            }

            if (p instanceof PesertaKarate) {
                karate++;
            } else if (p instanceof PesertaTaekwondo) {
                taekwondo++;
            }
        }

        System.out.println("\n===== REKAP " + NAMA_EVENT.toUpperCase() + " =====");
        System.out.println("Peserta Karate    : " + karate);
        System.out.println("Peserta Taekwondo : " + taekwondo);
        System.out.println("Total Peserta     : " + daftarPeserta.size());
        System.out.println("Total Pendapatan  : Rp" + (long) total);
    }
}
