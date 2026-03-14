/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algos_programmieraufgabe1;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Kurswert implements Serializable {
       private LocalDate date;
       private double close;
       private long volume;
       private double open;
       private double high;
       private double low;

    public Kurswert(LocalDate date, double close, long volume, double open, double high, double low) {
        this.date = date;
        this.close = close;
        this.volume = volume;
        this.open = open;
        this.high = high;
        this.low = low;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }
    
       
       
}
