/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algos_programmieraufgabe1;

import java.io.*;

public class CustomHashTable implements Serializable {
    private Aktie[] arr;
    private int aktieCounter = 0;

    /**
     * Constructor for CustomHashTable, initializes the array to hold the stocks
     */
    CustomHashTable() {
        arr = new Aktie[4001];
    }

    /**
     * Generates a hash value
     * @param n the string to generate the hash value for
     * @return the generated hash value
     */
    public static int generateHash(String n) {
        int hash = 0;
        for (char c : n.toCharArray()) {
            hash = 31 * hash + c;

        }
        return Math.abs(hash % 2003);
    }
    
    /**
     * Prints the menu for the user
     */
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

    /**
     * Searches for an empty index using quadratic probing
     * @param arr the array to search in
     * @param startIndex the index to start searching from
     * @param arraySize the size of the array
     * @return the index of the empty slot, or -1 if not found
     */
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

    /**
     * Searches for a stock using quadratic search
     * @param arr the array to search in
     * @param startIndex the index to start searching from
     * @param arraySize the size of the array
     * @param searchValue the value to search for
     * @return the index of the stock if found, or -1 if not found
     */
    public static int quadraticSearch(Aktie[] arr, int startIndex, int arraySize, String searchValue) {
        int searchIndex = -1;
        for (int i = 1; i < arraySize; i++) {
            int index = (startIndex + i * i) % arraySize;

            if (arr[index] == null)
                return -1;

            if (arr[index].getName().equals(searchValue) ||
                arr[index].getKurzel().equals(searchValue)) {
                return index;
            }
        }
        return searchIndex;
    }

    /**
     * Adds a stock to the hashtable
     * @param a the stock to add
     */
    public void addAktie(Aktie a) {
        if (aktieCounter < 1000) {
            int hashName = CustomHashTable.generateHash(a.getName());
            if (arr[hashName] != null) {
                hashName = CustomHashTable.quadraticProbe(arr, hashName, arr.length);

            }
            if (hashName == -1) {
                System.out.println("Aktie kann nicht in einen index hinzugefuegt werden");
                return;
            }
            arr[hashName] = a;
            System.out.println("Aktie (Name) in index " + hashName + " hinzugefuegt");
            
            int hashKuerzel = generateHash(a.getKurzel());
            if (arr[hashKuerzel] != null) {
                hashKuerzel = quadraticProbe(arr, hashKuerzel, arr.length);
            }

            if (hashKuerzel == -1) {
                System.out.println("Aktie kann nicht eingefuegt werden");
                return;
            }

            arr[hashKuerzel] = a;
            System.out.println("Aktie (Kuerzel) in index " + hashKuerzel + " hinzugefuegt");
            aktieCounter++;
        } else {
            System.out.println("Sie duerfen nicht mehr als 1000 Aktien verwalten");
        }
    }

    /**
     * Searches for a stock with the given name or abbreviation
     * @param value the value to search for
     * @return the stock if found, null otherwise
     */
    public Aktie searchAktie(String value) {

        int hash = generateHash(value);

        if (arr[hash] != null) {
            if (arr[hash].getName().equals(value) || arr[hash].getKurzel().equals(value)) {
                return arr[hash];
            }
        }

        int index = quadraticSearch(arr, hash, arr.length, value);

        if (index == -1 || arr[index] == null) {
            System.out.println("Aktie wurde nicht gefunden");
            return null;
        }

        return arr[index];
    }

    /**
     * Deletes the stock with the given name
     * @param name the name of the stock to delete
     */
    public void deleteAktie(String name) {
        int hash = CustomHashTable.generateHash(name);
        Aktie a = searchAktie(name);
        if (a != null) {
            a.deleteAktie();
            System.out.println("Aktie " + name + " wurde erfolgreich geloescht");
        }
    }

    /**
     * prints the chart for the given stock name
     * @param name the name of the stock to print the chart for
     */
    public void printChart(String name) {
        Aktie a = searchAktie(name);
        if (a == null) {
            System.out.println("Aktie " + name + " wurde nicht gefunden");
            return;
        }
        a.showKurswerte();

    }

    /**
     * Saves the hashtable to a file
     * @param filename the name of the file to save the hashtable to
     */
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

    /**
     * Loads the hashtable from a file
     * @param filename the name of the file to load the hashtable from
     */
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
