/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algos_programmieraufgabe1;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Aktie implements Serializable {
    private String name;
    private String WKN;
    private String kurzel;
    private Kurswert[] kurswerte;
    private int kursWertCounter = 0;

    /**
     * Constructor for Aktie, initializes the name, WKN, kurzel and an array to hold the stock values
     * @param name the name of the stock
     * @param WKN the WKN of the stock
     * @param kurzel the kuerzel of the stock
     */
    Aktie(String name,String WKN, String kurzel){
        this.name = name;
        this.WKN = WKN;
        this.kurzel = kurzel;
        kurswerte = new Kurswert[30];
        
    }
     
    public String getName(){
        return name;
    }
    
    public String getKurzel(){
        return kurzel;
    }
    
    public String getWKN(){
        return WKN;
    }
    
    /**
     * Adds a stock value to the array
     * @param k the stock value to add
     */
    public void addKurswert(Kurswert k){
        if(kursWertCounter < 30){
        kurswerte[kursWertCounter] = k;
        kursWertCounter++;
        } else {
            System.out.println("Sie duerfen nicht mehr als 30 Kurswerte pro aktie Verwalten!");
        }
    }
    
    /**
     * Displays the stock values in a chart
     */
    public void showKurswerte(){
        System.out.println("");
        double min=0,max=0;
        for(Kurswert k:kurswerte){
            if(k!=null && (k.getClose()<min || min==0)) {
                min=k.getClose();
            }
            if(k!=null && (k.getClose()>max || max==0)) {
                max=k.getClose();
            }
        }

        double range = max-min;
        int height=15;
        int days=30;

        // Chart zeichnen
        for (int h = height; h >= 0; h--) {

            double level = min + (range / height) * h;
            System.out.printf("%6.2f |", level);

            for (int d = 0; d < days; d++) {

                if (kurswerte[d] == null) {
                    System.out.print(" ");
                    continue;
                }

                double value = kurswerte[d].getClose();
                int scaled = (int)((value - min) / range * height);

                if (scaled == h)
                    System.out.print("  *    ");
                else
                    System.out.print("       ");
            }

            System.out.println();
        }

        // X Achse
        System.out.print("       ");
        for (Kurswert k: kurswerte) {
            if (k == null)
                break;
            System.out.print("-------");
        }

        System.out.println();

        System.out.print("       ");
        for (Kurswert k: kurswerte) {
            if (k==null)
                break;
            System.out.printf(" %02d/%02d ", k.getDate().getMonthValue(),k.getDate().getDayOfMonth());
        }


        System.out.println();

    }
    
    /**
     * Deletes the stock
     */
    public void deleteAktie(){
        this.WKN = "";
        this.kurswerte = null;
        this.kursWertCounter = 0;
        this.kurzel = "";
        this.name = "DELETED";
        
    }
    /**
     * Displays the stock information
     */
    public void showAktie(){
        System.out.println("---Gefundene Aktie---");
        System.out.println("Name:" + this.name);
        System.out.println("Kurzel:" + this.kurzel);
        System.out.println("WKN:" + this.WKN);
        if(kursWertCounter != 0){
            System.out.println("----------------------Daten----------------------");
            System.out.println("Date,Close,Volume,Open,High,Low");
            for(int i=0;i<kursWertCounter;i++){
                System.out.println(""+ kurswerte[i].getDate() + "," + kurswerte[i].getClose() + "," + kurswerte[i].getVolume() + "," + kurswerte[i].getOpen() + "," + kurswerte[i].getHigh()+ "," + kurswerte[i].getLow());
            }
        }
    }
}
