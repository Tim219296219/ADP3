/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.createstakeholder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim
 */
public class Main {
    private FileInputStream fi;
    private ObjectInputStream input;
    private ArrayList<Customer> cList = new ArrayList<>();
    private ArrayList<Supplier> sList = new ArrayList<>();
    private FileWriter fw;
    private PrintWriter pw;
    private int[] age = new int[20];
    
    public void readingTheFile() {
        try {
            fi = new FileInputStream("stakeholder.ser");
            input = new ObjectInputStream(fi);
            while(true) {
                
                Object object = input.readObject();
                if(object instanceof Customer) {
                    cList.add((Customer) object);
      
                } else if(object instanceof Supplier) {
                    sList.add((Supplier) object);
                }
            }
        } catch(IOException ex) {
            System.out.println("Error reading the file"); 
        } catch(ClassNotFoundException ex) {
            System.out.println("|");
           
        }
        for (int i = 0; i < cList.size(); i++) {
            System.out.println(cList.get(i));
            
            
            
        }
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i));
        }
        
    }
    public void getAge() {
        for(int i = 0; i < cList.size(); i++) {
            
            String DOB = cList.get(i).getDateOfBirth();
            String cName = cList.get(i).getSurName();
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            
            try{
                date = df.parse(DOB);
                
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                int cYOB = c.get(Calendar.YEAR);

                
                Calendar cl = Calendar.getInstance();
                int currentYear = cl.get(Calendar.YEAR);
                
                age[i] =  currentYear - cYOB;
                
                System.out.printf("%s is %d years old\n", cName, age[i]);
                
            }catch(ParseException ex){
                
            }
        } 
        
    }
    
    public void writeCustomerFile() {
        try{
            fw = new FileWriter("customerOutFile.txt");
            pw = new PrintWriter(fw);
            
            pw.write(String.format("%s\n", "==========================[ Customer ]=========================="));
            pw.write(String.format("%-10s %-10s %-15s %-20s %-10s\n", "ID", "Name", "Surname", "Date Of Birth", "Age"));
            pw.write(String.format("%s\n", "================================================================"));
            
            for(int i = 0; i < cList.size(); i++){
                pw.write(String.format("%-10s %-10s %-15s %-20s %-10d\n", cList.get(i).getStHolderId(), cList.get(i).getFirstName(), cList.get(i).getSurName(), cList.get(i).getDateOfBirth(), age[i]));
            }
            pw.write(String.format("%s\n", "================================================================"));
            
            
            
            pw.close();
        }
        catch(IOException ex) {
            System.out.println("error writing to file");
        }
    }
}

     
         
    
     
       