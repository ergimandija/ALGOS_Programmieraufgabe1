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
    
    public void addKurswert(Kurswert k){
        if(kursWertCounter < 30){
        kurswerte[kursWertCounter] = k;
        kursWertCounter++;
        } else {
            System.out.println("Sie duerfen nicht mehr als 30 Kurswerte pro aktie Verwalten!");
        }
    }
    
    public void showKurswerte(){
        System.out.println("");
        for(Kurswert k:kurswerte){
            
        }
    }
    
    public void deleteAktie(){
        this.WKN = "";
        this.kurswerte = null;
        this.kursWertCounter = 0;
        this.kurzel = "";
        this.name = "DELETED";
        
    }
    
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
