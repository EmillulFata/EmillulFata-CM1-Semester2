import java.util.Scanner;

public class DataMain07 {
    public static void main(String[] args) {
        SistemPerpustakaan sistem = new SistemPerpustakaan();
        sistem.initData();
        Scanner sc = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    sistem.tampilMahasiswa();
                    break;
                case 2:
                    sistem.tampilBuku();
                    break;
                case 3:
                    sistem.tampilDataPeminjaman();
                    break;
                case 4:
                    sistem.insertionSortDenda();
                    System.out.println("\nSetelah diurutkan (Denda terbesar):");
                    sistem.tampilDataPeminjaman(); // Memanggil ulang list setelah disort
                    break;
                case 5:
                    sistem.insertionSortNIM(); // Sort dulu agar binary search jalan
                    System.out.print("Masukkan NIM: ");
                    String nim = sc.nextLine();
                    int hasil = sistem.binarySearch(nim);
                    if (hasil != -1) {
                        sistem.data[hasil].tampil();
                    } else {
                        System.out.println("Data tidak ditemukan!");
                    }
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Terima Kasih telah menggunakan sistem perpustakaan kami...");
                    break;
                default:
                    System.out.println("Menu tidak ada!");
            }
        } while (pilih != 0);
        sc.close();
    }
}