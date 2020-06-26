/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfileverification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishant.singh
 */
public class InputCSVFile {

    String pathCSV;
    String seperator;
    ArrayList<String> ignoreAttribute;
    ArrayList<String[]> fullData;
    

    public InputCSVFile(String pathCSV, ArrayList<String> ignoreAttribute,String seperator) {
        this.pathCSV = pathCSV;
        this.ignoreAttribute = ignoreAttribute;
        this.seperator=seperator;
        //System.out.println("seperator is :-->  "+seperator);
        getdata();
    }

    public String getPathCSV() {
        return pathCSV;
    }

    public ArrayList<String> getIgnoreAttribute() {
        return ignoreAttribute;
    }

    public void getdata() {

        FileInputStream inputStream = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            inputStream = inputStream = new FileInputStream(pathCSV);
            Scanner sc1 = null;
            sc1 = new Scanner(inputStream, "UTF-8");
            while (sc1.hasNext()) {
                String currnetline = sc1.nextLine();
                result.add(currnetline.split(seperator));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Provided path is not found" + pathCSV);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                System.out.println("Provided path is not found" + pathCSV);
            }
        }
        this.fullData=result;
    }//put data in fullData
    
    public String[] getheader()
    {
        return fullData.get(0);
    }

    public ArrayList<String[]> getFullData() {
        return fullData;
    }
    
    public ArrayList<String[]> getFullDataWithoutHeader() {
        ArrayList<String[]> result=fullData;
        result.remove(0);
        return result;
    }

    
    public int getHeaderIndex(String columnName)
    {
        int result=-1;
        String data[]=getheader();
        for(int i=0;i<data.length;i++)
        {
            if(data[i].equals(columnName))
            {
                result=i;
                break;
            }
        }
        
        return result;
    }
            
}
