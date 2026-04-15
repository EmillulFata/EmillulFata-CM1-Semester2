import java.util.Scanner;

class SistemPerpustakaan {
    Peminjaman[] data = new Peminjaman[5];

    void initData() {
        Mahasiswa m1 = new Mahasiswa("22001", "Andi", "Teknik Informatika");
        Mahasiswa m2 = new Mahasiswa("22002", "Budi", "Teknik Informatika");
        Mahasiswa m3 = new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis");

        Buku b1 = new Buku("B001", "Algoritma", 2020);
        Buku b2 = new Buku("B002", "Basis Data", 2019);
        Buku b3 = new Buku("B003", "Pemrograman", 2021);
        Buku b4 = new Buku("B004", "Fisika", 2024);

        data[0] = new Peminjaman(m1, b1, 7);
        data[1] = new Peminjaman(m2, b2, 3);
        data[2] = new Peminjaman(m3, b3, 10);
        data[3] = new Peminjaman(m3, b4, 6);
        data[4] = new Peminjaman(m1, b2, 4);
    }

    void tampilMahasiswa() {
        System.out.println("\n[ DATA MAHASISWA ]");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-25s\n",
                "NIM", "Nama", "Program Studi");
        System.out.println("------------------------------------------------------------------");

        String[] printed = new String[data.length];
        int count = 0;
        for (Peminjaman p : data) {
            boolean isDuplicate = false;
            for (int i = 0; i < count; i++) {
                if (printed[i] != null && printed[i].equals(p.mhs.nim))
                    isDuplicate = true;
            }
            if (!isDuplicate) {
                System.out.printf("%-10s | %-15s | %-25s\n",
                        p.mhs.nim, p.mhs.nama, p.mhs.prodi);
                printed[count++] = p.mhs.nim;
            }
        }
        System.out.println("------------------------------------------------------------------");
    }

    void tampilBuku() {
        System.out.println("\n[ DATA BUKU ]");
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-6s\n", "Kode", "Judul Buku", "Tahun");
        System.out.println("----------------------------------------------------------");

        String[] printed = new String[data.length];
        int count = 0;
        for (Peminjaman p : data) {
            boolean isDuplicate = false;
            for (int i = 0; i < count; i++) {
                if (printed[i] != null && printed[i].equals(p.buku.kode))
                    isDuplicate = true;
            }
            if (!isDuplicate) {
                System.out.printf("%-10s | %-20s | %-6d\n", p.buku.kode, p.buku.judul, p.buku.tahun);
                printed[count++] = p.buku.kode;
            }
        }
        System.out.println("----------------------------------------------------------");
    }

    void tampilDataPeminjaman() {
        System.out.println("\n[ DATA PEMINJAMAN ]");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-8s | %-13s | %-10s\n",
                "Nama", "Judul Buku", "Lama", "Terlambat", "Denda");
        System.out.println("--------------------------------------------------------------------------");
        for (Peminjaman p : data) {
            p.tampil();
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    void insertionSortDenda() {
        for (int i = 1; i < data.length; i++) {
            Peminjaman temp = data[i];
            int j = i - 1;
            while (j >= 0 && data[j].denda < temp.denda) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = temp;
        }
    }

    void insertionSortNIM() {
        for (int i = 1; i < data.length; i++) {
            Peminjaman temp = data[i];
            int j = i - 1;
            while (j >= 0 && data[j].mhs.nim.compareTo(temp.mhs.nim) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = temp;
        }
    }

    int binarySearch(String nim) {
        int left = 0, right = data.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid].mhs.nim.equals(nim))
                return mid;
            if (data[mid].mhs.nim.compareTo(nim) < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}

public class SistemPerpuskaan07 {
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