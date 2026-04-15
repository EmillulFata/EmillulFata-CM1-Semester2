class Peminjaman {
  Mahasiswa mhs;
  Buku buku;
  int lamaPinjam;
  int denda;

  Peminjaman(Mahasiswa mhs, Buku buku, int lamaPinjam) {
    this.mhs = mhs;
    this.buku = buku;
    this.lamaPinjam = lamaPinjam;
    hitungDenda();
  }

  void hitungDenda() {
    int batas = 5;
    int dendaPerHari = 2000;

    denda = (lamaPinjam > batas)
        ? (lamaPinjam - batas) * dendaPerHari
        : 0;
  }

  void tampil() {
    int batas = 5;
    int terlambat = Math.max(0, lamaPinjam - batas);

    System.out.printf("%-10s | %-15s | %-5d | %-10d | %-6d\n",
        mhs.nama, buku.judul, lamaPinjam, terlambat, denda);
  }
}