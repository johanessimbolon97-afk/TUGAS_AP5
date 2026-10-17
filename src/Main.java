import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList; 

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.2 — CRUD Test ===\n");

        CharacterManager manager = new CharacterManager();

        // 1. Tambah beberapa karakter
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));
        
        // Menambahkan satu WARRIOR lagi untuk uji coba findByType
        manager.addCharacter(new Character("Gatotkaca", CharacterType.WARRIOR, 10));

        // 2. Coba tambah DUPLIKAT nama — seharusnya DITOLAK
        System.out.println();
        manager.addCharacter(new Character("Arjuna", CharacterType.HEALER, 1));

        // 3. Tampilkan total karakter
        System.out.println("\nTotal karakter saat ini: " + manager.getTotalCharacters());

        // 4. Cari karakter
        System.out.println("\n--- Pencarian ---");
        Character hasil = manager.findByName("Merlin");
        System.out.println(hasil != null ? "Ditemukan: " + hasil : "Tidak ditemukan");

        Character tidakAda = manager.findByName("Zeus");
        System.out.println(tidakAda != null ? "Ditemukan: " + tidakAda : "Zeus tidak ditemukan (sesuai harapan)");

        // 5. Hapus karakter
        System.out.println("\n--- Penghapusan ---");
        manager.removeCharacter("Athena");
        System.out.println("Total setelah hapus: " + manager.getTotalCharacters());

        // 6. Tampilkan semua karakter yang tersisa
        System.out.println("\n--- Daftar Karakter Tersisa ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }

        // ===== PENGUJIAN TANTANGAN MANDIRI =====
        System.out.println("\n--- Uji Tantangan Mandiri ---");
        
        // A. Uji getAverageLevel()
        double avgLevel = manager.getAverageLevel();
        System.out.println("⭐ Rata-rata level seluruh karakter: " + avgLevel);

        // B. Uji findByType()
        CharacterType targetType = CharacterType.WARRIOR;
        ArrayList<Character> warriors = manager.findByType(targetType);
        System.out.println("🛡️ Daftar karakter dengan tipe " + targetType + ":");
        if (warriors.isEmpty()) {
            System.out.println("Tidak ada karakter dengan tipe tersebut.");
        } else {
            for (Character w : warriors) {
                System.out.println(" - " + w);
            }
        }

        // C. Uji Fitur Mandiri (findStrongest)
        System.out.println("\n--- Uji Fitur Analitik Mandiri (findStrongest) ---");
        Character terkuat = manager.findStrongest();
        if (terkuat != null) {
            System.out.println("⚔️ Karakter terkuat saat ini: " + terkuat.getName() + 
                               " (Attack Power: " + terkuat.getAttackPower() + ")");
        } else {
            System.out.println("List karakter kosong.");
        }

        // ===== EKSPERIMEN: Object Reference =====
        System.out.println("\n--- Eksperimen Reference ---");

        Character asli = manager.findByName("Arjuna");
        Character duplikatReferensi = asli;             

        System.out.println("Level Arjuna sebelum: " + asli.getLevel());
        duplikatReferensi.setLevel(99);                  

        System.out.println("Level Arjuna sesudah (lewat 'asli'): " + asli.getLevel());
        System.out.println("asli == duplikatReferensi? " + (asli == duplikatReferensi));

        // Bandingkan dengan getAllCharacters() yang mengembalikan SALINAN LIST:
        java.util.ArrayList<Character> salinanList = manager.getAllCharacters();
        salinanList.add(new Character("Hantu", CharacterType.MAGE, 1));   

        System.out.println("Total di manager asli: " + manager.getTotalCharacters());
        System.out.println("Total di salinan list : " + salinanList.size());
    }
}
