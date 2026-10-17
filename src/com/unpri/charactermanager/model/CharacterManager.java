package com.unpri.charactermanager.model;

import java.util.ArrayList;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {

    // Diberi final sesuai saran warning IDE agar referensi list tidak berubah
    private final ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();   
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak jika sudah ada karakter dengan nama yang sama (cek via equals()).
     * @return true jika berhasil ditambahkan, false jika ditolak karena duplikat
     */
    public boolean addCharacter(Character character) {
        if (findByName(character.getName()) != null) {   
            System.out.println("❌ Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("✅ Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     * @return object Character jika ditemukan, null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character c : characterList) {         
            if (c.getName().equals(name)) {          
                return c;                             
            }
        }
        return null;                                  
    }

    /**
     * Menghapus karakter dari list berdasarkan nama.
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println("❌ Gagal hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);                 
        System.out.println("🗑️ Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan SALINAN list, bukan referensi asli.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);        
    }

    /**
     * Menghitung rata-rata level seluruh karakter dalam list.
     * Mengembalikan 0 jika list kosong.
     */
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0.0;
        }
        int totalLevel = 0;
        for (Character c : characterList) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characterList.size();
    }

    /**
     * Mengembalikan semua karakter yang memiliki tipe tertentu (misal: WARRIOR).
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Mencari dan mengembalikan karakter dengan attack power tertinggi dalam list.
     * Mengembalikan null jika list dalam keadaan kosong.
     * 
     * @return object Character dengan attack power tertinggi, atau null jika list kosong
     */
    public Character findStrongest() {
        if (characterList.isEmpty()) {
            return null;
        }

        Character strongest = characterList.get(0);
        for (Character c : characterList) {
            if (c.getAttackPower() > strongest.getAttackPower()) {
                strongest = c;
            }
        }
        return strongest;
    }
}