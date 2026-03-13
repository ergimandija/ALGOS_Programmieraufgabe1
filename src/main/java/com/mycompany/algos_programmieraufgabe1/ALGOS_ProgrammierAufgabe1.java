/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.algos_programmieraufgabe1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 *
 * @author User
 */
public class ALGOS_ProgrammierAufgabe1 {
    public static void main(String[] args) {
        
        
        System.out.println("----Uebung 1 - Hashtabelle----");
        System.out.println("");
      
        CustomHashTable htable = new CustomHashTable();
        int input;
        boolean keepRunning  = true;
        do {
            CustomHashTable.showMenu();
            Scanner scan  = new Scanner(System.in);
            input = scan.nextInt();
            scan.nextLine();

            switch(input){
                case 1: 
                    System.out.println("Enter Name:");
                    String name = scan.nextLine();
                    System.out.println("Enter WKN:");
                    String wkn = scan.nextLine();
                    System.out.println("Enter Kuerzel:");
                    String kurzel = scan.nextLine();
                    htable.addAktie(new Aktie(name,wkn,kurzel));
                    break;
                case 2:
                    System.out.println("Geben Sie den Namen der zu loeschenden Aktie ein:");
                    htable.deleteAktie(scan.nextLine());
                    break;
                case 3: {
                    System.out.println("Geben Sie den Namen der Aktie ein, in die die Daten importiert werden sollen:");
                    String searchedName = scan.nextLine();
                    Aktie a = htable.searchAktie(searchedName);
                    if(a== null){
                        return;
                    }
                    System.out.println("Geben Sie den Namen der zu importierenden CSV-Datei ein:");
                    
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(scan.nextLine()));
                        br.readLine();
                        String line;
                        while((line = br.readLine())!= null){                             
                            String parts[] = line.split(",");
                            LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                            double close = Double.parseDouble(parts[1].replace("$", ""));
                            long volume = Long.parseLong(parts[2]);
                            double open = Double.parseDouble(parts[3].replace("$", ""));
                            double high = Double.parseDouble(parts[4].replace("$", ""));
                            double low = Double.parseDouble(parts[5].replace("$", ""));
                            Kurswert k = new Kurswert(date,close,volume,open,high,low);
                            a.addKurswert(k);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading file.");
                    }
                
                    break;
                }
                case 4: {
                    System.out.println("Geben Sie den Namen der gesuchten Aktie ein:");
                    String searchedName = scan.nextLine();
                    Aktie a = htable.searchAktie(searchedName);
                    if(a!= null){
                       a.showAktie();
                    }
                    break;
                }
                case 5: {
                    
                    break;
                }
                    
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    keepRunning  = false;
                    break;
                
            }
            
        } while(keepRunning);
        
        
    }
}
