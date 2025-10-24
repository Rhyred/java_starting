
// Kita perlu mengimpor library Java untuk Array (mencetak) dan LinkedList
import java.util.Arrays;
import java.util.LinkedList;

/**
 * File source code tunggal untuk menjawab Tugas 2 (Soal 1-5).
 * Program ini akan mendeklarasikan semua variabel, array, dan list,
 * lalu mencetaknya ke konsol.
 */
public class Tugas2 {

    public static void main(String[] args) {

        System.out.println("===== Hasil Pengerjaan Tugas 2 =====");

        // --- Soal 1 ---
        // Deklarasi variabel integer 'StrukturBaris'.
        // "Deklarasi" berarti 'memperkenalkan' variabel ke program.
        int StrukturBaris;

        // Agar bisa dicetak, kita beri nilai (inisialisasi)
        StrukturBaris = 10;
        System.out.println("1. Isi variabel StrukturBaris: " + StrukturBaris);

        // --- Soal 2 ---
        // Deklarasi dan inisialisasi variabel string 'KataBaru'.
        String KataBaru = "Deklarasi tipe data String";
        System.out.println("2. Isi variabel KataBaru: " + KataBaru);

        // --- Soal 3 ---
        // Deklarasi array satu dimensi 'empatAngka' dengan nilai.
        // Tanda kurung siku [] menandakan ini adalah array.
        // Tanda kurung kurawal {} digunakan untuk langsung mengisi nilainya.
        int[] empatAngka = { 07, 10, 20, 23 };

        // Kita gunakan 'Arrays.toString()' agar isi array tercetak rapi
        System.out.println("3. Isi array empatAngka: " + Arrays.toString(empatAngka));

        // --- Soal 4 ---
        // Deklarasi array dua dimensi 'Angka' (3 baris x 3 kolom).
        // Tipe datanya adalah String, jadi semua angka harus di dalam tanda kutip "".
        String[][] Angka = {
                { "1", "3", "5" }, // Baris 1
                { "14", "19", "20" }, // Baris 2
                { "22", "27", "29" } // Baris 3
        };

        // Untuk array 2D, kita gunakan 'Arrays.deepToString()'
        System.out.println("4. Isi array dua dimensi Angka: " + Arrays.deepToString(Angka));

        // --- Soal 5 ---
        // Deklarasi LinkedList 'listAngka' dengan tipe data Integer.
        // Kita harus 'mengimpor' 'java.util.LinkedList' di atas file ini.
        // Cara termudah mengisinya saat deklarasi adalah menggunakan 'Arrays.asList()'.
        LinkedList<Integer> listAngka = new LinkedList<Integer>(
                Arrays.asList(22, 19, 44, 60, 72));

        System.out.println("5. Isi LinkedList listAngka: " + listAngka);

        System.out.println("======================================");
    }
}