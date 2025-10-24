==================================================================
== PANDUAN VIDEO PENJELASAN - TUGAS 1 (Aplikasi Restoran) ==
==================================================================

(PERSIAPAN: Buka file Menu.java dan Main.java di VS Code)

0. PEMBUKAAN
   - "Halo, perkenalkan nama saya [Nama Kamu]. Di sini saya akan menjelaskan Tugas Praktik 1, yaitu membuat program aplikasi kasir restoran sederhana."

1. STRUKTUR PROGRAM (OOP)
   - "Sesuai petunjuk, program ini terdiri dari dua file: Menu.java dan Main.java."
   - (Tunjukkan file Menu.java) "File 'Menu.java' ini adalah sebuah 'Class' atau blueprint. Ini memenuhi Indikator 1 (Tipe Data, Variabel, Class)."
   - "Di sini kita deklarasikan atribut yang akan dimiliki setiap menu, yaitu 'nama' (String), 'harga' (double), dan 'kategori' (String)."
   - (Tunjukkan file Main.java) "File 'Main.java' adalah program utama yang akan menjalankan semua logika kasirnya."

2. INPUT MENU (ARRAY)
   - (Di Main.java, tunjukkan bagian inisialisasi array 'daftarMenu')
   - "Di sini, program 'menginput' data menu-nya. Sesuai petunjuk, data disimpan dalam sebuah Array bernama 'daftarMenu'."
   - "Setiap elemen array adalah 'Object' Menu baru, yang kita buat menggunakan blueprint dari 'Menu.java' tadi. Ini memenuhi Indikator 2 (Implementasi Array)."

3. (OPSIONAL TAPI BAGUS) MODE ADMIN
   - "Saya juga menambahkan fitur 'Mode Admin' di awal program."
   - "Fitur ini menggunakan struktur keputusan 'if' untuk bertanya apakah user mau mengedit menu, dan 'switch-case' untuk memilih menu nomor berapa yang mau diedit."
   - "Ini juga bagian dari implementasi Indikator 3 (Struktur Keputusan)."

4. PROSES PEMESANAN (LOGIKA INTI)
   - "Kita lanjut ke bagian pemesanan. Program akan meminta user menginput nama menu."
   - (Tunjukkan blok 'if-else if' yang panjang untuk 'pesanan1')
   - "Ini adalah inti dari Indikator 3 (Struktur Keputusan). Karena kita dilarang pakai 'loop', program harus mengecek input 'pesanan1' satu per satu ke setiap nama menu di array."
   - "Saya pakai '.trim()' untuk menghapus spasi ekstra, dan '.equalsIgnoreCase()' agar input tidak case-sensitive."
   - "Blok 'if-else if' yang sangat panjang ini diulang lagi untuk 'pesanan2', 'pesanan3', dan 'pesanan4', karena kita tidak boleh pakai 'loop'."

5. PERHITUNGAN TOTAL (SKENARIO STRUKTUR KEPUTUSAN)
   - (Tunjukkan bagian kalkulasi total biaya)
   - "Setelah semua pesanan diproses, kita masuk ke skenario perhitungan total. Di sini banyak sekali Struktur Keputusan yang dipakai."
   - "Skenario pertama: Diskon 10%. Saya pakai 'if-else' sederhana. Jika subTotal > 100.000, maka dapat diskon."
   - "Skenario kedua: Promo BOGO (Beli 1 Gratis 1 Minuman). Saya pakai 'if' untuk mengecek apakah subTotal > 50.000."
   - "Di dalamnya, saya pakai 'Nested If' (if bersarang) untuk mencari minuman termurah yang dipesan oleh pelanggan."
   - "Skenario ketiga: Pajak 10% dan Biaya Pelayanan Rp 20.000."

6. CETAK STRUK
   - (Tunjukkan bagian 'Mencetak Struk Pesanan')
   - "Terakhir, program akan mencetak struk. Di sini saya pakai struktur 'if' tunggal."
   - "Contohnya, 'if (jumlah1 > 0)', artinya detail pesanan 1 hanya akan dicetak jika pesanannya valid dan jumlahnya lebih dari 0."
   - "Hal yang sama berlaku untuk menampilkan diskon, 'if (diskon > 0)'."

7. DEMO SINGKAT PROGRAM
   - (Jalankan programnya di terminal VS Code)
   - (Contoh Demo):
     - Jawab 'y' untuk mode admin.
     - Edit menu nomor 1 (Nasi Padang), ganti harga jadi "30000".
     - Program akan tampilkan menu final.
     - Pesan "nasi padang" (pakai huruf kecil) jumlah 1.
     - Pesan "jus alpukat" jumlah 2.
     - Kosongkan pesanan 3 dan 4.
     - (Tunjukkan struknya) "Ini hasilnya. Harga Nasi Padang sudah berubah jadi 30000. Totalnya adalah... (jelaskan sedikit totalnya). "

8. PENUTUP
   - "Sekian penjelasan saya untuk program Tugas 1. Terima kasih."


==================================================================
== PANDUAN VIDEO PENJELASAN - TUGAS 2 (Deklarasi) ==
==================================================================

(PERSIAPAN: Buka file Tugas2.java di VS Code)

0. PEMBUKAAN
   - "Halo, perkenalkan nama saya [Nama Kamu]. Di video ini saya akan menjelaskan pengerjaan Tugas 2."
   - "Sesuai petunjuk, saya membuat 1 file 'Tugas2.java' yang berisi jawaban untuk kelima soal."

1. IMPORTS
   - (Tunjukkan bagian 'import' di atas)
   - "Pertama, saya mengimpor 'java.util.Arrays' dan 'java.util.LinkedList'. Ini adalah library yang dibutuhkan untuk Soal 3, 4, dan 5."

2. SOAL 1 (Variabel Integer)
   - (Tunjukkan kode Soal 1)
   - "Untuk Soal 1, saya mendeklarasikan variabel integer bernama 'StrukturBaris' dengan syntax 'int StrukturBaris;'."
   - (Jalankan program/tunjukkan output) "Hasilnya '10' setelah saya inisialisasi."

3. SOAL 2 (Variabel String)
   - (Tunjukkan kode Soal 2)
   - "Untuk Soal 2, saya deklarasikan variabel String 'KataBaru' dan langsung mengisinya dengan nilai 'Deklarasi tipe data String'."
   - (Tunjukkan output) "Hasilnya..."

4. SOAL 3 (Array Satu Dimensi)
   - (Tunjukkan kode Soal 3)
   - "Untuk Soal 3, ini adalah deklarasi array satu dimensi integer bernama 'empatAngka'. Saya gunakan 'int[]' dan kurung kurawal '{}' untuk langsung mengisi nilainya."
   - (Tunjukkan output) "Untuk mencetaknya dengan rapi, saya pakai 'Arrays.toString()'."

5. SOAL 4 (Array Dua Dimensi)
   - (Tunjukkan kode Soal 4)
   - "Untuk Soal 4, ini adalah array dua dimensi 3x3 bernama 'Angka' dengan tipe data 'String[][]'."
   - "Karena tipe datanya String, semua angkanya harus pakai tanda kutip. Untuk mencetaknya, saya pakai 'Arrays.deepToString()'."
   - (Tunjukkan output) "Hasilnya..."

6. SOAL 5 (Linked List)
   - (Tunjukkan kode Soal 5)
   - "Terakhir, Soal 5 adalah 'LinkedList'. Saya deklarasikan dengan 'LinkedList<Integer> listAngka'."
   - "Tanda '<Integer>' ini wajib untuk menentukan tipe data di dalam list. Saya pakai 'Arrays.asList()' sebagai cara cepat untuk mengisi list-nya saat deklarasi."
   - (Tunjukkan output) "Hasilnya..."

7. PENUTUP
   - "Sekian penjelasan saya untuk kelima soal di Tugas 2. Terima kasih."
