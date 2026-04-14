// ================== CLASS MAHASISWA ==================
class Data07 {
  String nim, nama, prodi;

  Data07(String nim, String nama, String prodi) {
    this.nim = nim;
    this.nama = nama;
    this.prodi = prodi;
  }
}

// ================== CLASS BUKU ==================
class Buku {
  String kode, judul;
  int tahun;

  Buku(String kode, String judul, int tahun) {
    this.kode = kode;
    this.judul = judul;
    this.tahun = tahun;
  }
}

// ================== CLASS PEMINJAMAN ==================
class Peminjaman {
  Data07 mhs;
  Buku buku;
  int lamaPinjam;
  int denda;

  Peminjaman(Data07 mhs, Buku buku, int lamaPinjam) {
    this.mhs = mhs;
    this.buku = buku;
    this.lamaPinjam = lamaPinjam;
    hitungDenda();
  }

  void hitungDenda() {
    int batas = 5;
    int dendaPerHari = 2000;
    if (lamaPinjam > batas) {
      denda = (lamaPinjam - batas) * dendaPerHari;
    } else {
      denda = 0;
    }
  }

  // Method tampil untuk data transaksi (Menu 3, 4, 5)
  void tampil() {
    int batas = 5;
    int terlambat = (lamaPinjam > batas) ? (lamaPinjam - batas) : 0;
    // Format printf agar sejajar dengan header di tampilData()
    System.out.printf("%-10s | %-15s | Lama: %-2d | Terlambat: %-2d | Denda: %-6d\n",
        mhs.nama, buku.judul, lamaPinjam, terlambat, denda);
  }
}

// ================== CLASS MANAGER ==================
class SistemPerpustakaan {
  Peminjaman[] data = new Peminjaman[5];

  void initData() {
    Data07 m1 = new Data07("22001", "Andi", "Teknik Informatika");
    Data07 m2 = new Data07("22002", "Budi", "Teknik Informatika");
    Data07 m3 = new Data07("22003", "Citra", "Sistem Informasi Bisnis");

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
    System.out.println  ("----------------------------------------------------------");
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

  void tampilData() {
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

  void insertionSort() {
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