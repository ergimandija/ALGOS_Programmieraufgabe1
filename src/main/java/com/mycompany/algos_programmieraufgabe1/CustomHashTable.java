/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algos_programmieraufgabe1;

import java.io.*;

/**
 *
 * @author User
 */
public class CustomHashTable implements Serializable {
    private Aktie[] arr;
    private int aktieCounter = 0;

    CustomHashTable() {
        arr = new Aktie[2003];
    }

    public static int generateHash(String n) {
        int hash = 0;
        for (char c : n.toCharArray()) {
            hash = 31 * hash + c;

        }
        return Math.abs(hash % 2003);
    }

    public static void showMenu() {
        System.out.println("---Menu---");
        System.out.println(
                "1. ADD: Fuege eine Aktie mit Namen, WKN und Kuerzel hinzu.\n" +
                        "2. DEL: Loesche eine Aktie.\n" +
                        "3. IMPORT: Importiere Kurswerte einer Aktie aus einer CSV-Datei.\n" +
                        "4. SEARCH: Suche nach einer Aktie.\n" +
                        "5. PLOT: Gib die Schlusskurse der letzten 30 Tage als ASCII-Grafik aus.\n" +
                        "6. SAVE <filename>: Speichere die Hashtabelle in einer Datei.\n" +
                        "7. LOAD <filename>: Lade die Hashtabelle aus einer Datei.\n" +
                        "8. QUIT: Beende das Programm."
        );
    }

    public static int quadraticProbe(Aktie[] arr, int startIndex, int arraySize) {
        int emptyIndex = -1;
        for (int i = 1; i < arraySize; i++) {
            if (arr[(startIndex + i * i) % arraySize] == null) {
                emptyIndex = (startIndex + i * i) % arraySize;
                break;
            }
        }
        return emptyIndex;
    }

    public static int quadraticSearch(Aktie[] arr, int startIndex, int arraySize, String searchValue) {
        int searchIndex = -1;
        for (int i = 1; i < arraySize; i++) {
            int index = (startIndex + i * i) % arraySize;

            if (arr[index] == null)
                return -1;

            if (arr[index].getName().equals(searchValue)) {
                return index;
            }
        }
        return searchIndex;
    }


    public void addAktie(Aktie a) {
        if (aktieCounter < 1000) {
            int hash = CustomHashTable.generateHash(a.getName());
            if (arr[hash] != null) {
                hash = CustomHashTable.quadraticProbe(arr, hash, arr.length);

            }
            if (hash == -1) {
                System.out.println("Aktie kann nicht in einen index hinzugefuegt werden");
                return;
            }
            arr[hash] = a;
            System.out.println("Aktie in index " + hash + " hinzugefuegt");
            aktieCounter++;
        } else {
            System.out.println("Sie duerfen nicht mehr als 1000 Aktien verwalten");
        }
    }


    public Aktie searchAktie(String name) {
        int hash = CustomHashTable.generateHash(name);
        if (arr[hash] != null && !arr[hash].getName().equals(name)) {
            hash = CustomHashTable.quadraticSearch(arr, hash, arr.length, name);
        }
        if (hash == -1 || arr[hash] == null) {
            System.out.println("Aktie ist nicht gefunden");
            return null;

        }
        return arr[hash];
    }

    public void deleteAktie(String name) {
        int hash = CustomHashTable.generateHash(name);
        Aktie a = searchAktie(name);
        if (a != null) {
            a.deleteAktie();
            System.out.println("Aktie " + name + " wurde erfolgreich geloescht");
        }
    }

    public void save(String filename) {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(filename));

            out.writeObject(arr);

            out.close();
            System.out.println("Hashtabelle gespeichert.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String filename) {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(filename));

            Aktie[] table = (Aktie[]) in.readObject();

            in.close();
            System.out.println("Hashtabelle geladen.");

            this.arr = table;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}   
