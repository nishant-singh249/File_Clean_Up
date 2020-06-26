/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfileverification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Nishant.singh
 */
public class RemoveEntryFromCSV {

    Properties Property;
    InputCSVFile file;
    String[] uniqueCombination;
    ArrayList<String[]> rawData;
    String seperator;
    ArrayList<String> ignoreAttribute;
    ArrayList<String[]> optiData;
    ArrayList<String[]> optiData1;

    public RemoveEntryFromCSV(String file,int ind) {

        setProperty();
        this.seperator = Property.getProperty("seperator");
        String[] uniqueCombination = Property.getProperty("uniqueCombination").toString().split(",");
        this.uniqueCombination = uniqueCombination;
        this.file = new InputCSVFile(file, ignoreAttribute, seperator);
        
        setRawData();
        setoptdata();
        if(ind==1)
        {
        removeAplhanumeric();
        }

        else if(ind==2)
        {
        	removeSize();
        }
        else if(ind==3)
        {
        	setoptiData();
        }
      //setoptiData();
        //removeSize();
    }

    public void setIgnoreAttribute() {
        String[] data = Property.get("ignoreAttribute").toString().split(",");
        ArrayList<String> ignoreAttribute = new ArrayList<String>();
        for (String x : data) {
            ignoreAttribute.add(x);
        }
        this.ignoreAttribute = ignoreAttribute;
    }

    public void setProperty() {
        ReadProperties read = new ReadProperties();
        this.Property = read.getPropertiesObject();
    }

    public void setRawData() {
        this.rawData = file.getFullData();
    }

    public void setoptdata()
    {
    	this.optiData = this.rawData;
    }
    public void setoptiData() {
        //this.optiData = this.rawData;
        int index1 = file.getHeaderIndex(uniqueCombination[0]);
        int index2 = file.getHeaderIndex(uniqueCombination[1]);
        for (int i = 0; i < optiData.size(); i++) {

            for (int j = i + 1; j < optiData.size(); j++) {
                if (optiData.get(i)[index1].equals(optiData.get(j)[index1])) {
                    if (optiData.get(i)[index2].equals(optiData.get(j)[index2])) {
                        System.out.println(optiData.get(i)[index2]);
                        System.out.println(optiData.get(j)[index2]);
                        optiData.remove(j);
                        j--;
                    }

                }
            }
            /*if(optiData.get(i)[index1].length()>15)
                    {
                         optiData.remove(i);
                    }
            
            if(!isNum(optiData.get(i)[index1])&&i>0)
            {
                optiData.remove(i);
            } */
        }

        this.optiData1 = optiData;

    }

    public void removeAplhanumeric() {
        //this.optiData = this.rawData;
        int index1 = file.getHeaderIndex(uniqueCombination[0]);
        int index2 = file.getHeaderIndex(uniqueCombination[1]);

        for (int i = 0; i < optiData.size(); i++) {

            if (!isNum(optiData.get(i)[index1]) && i > 0) {
                optiData.remove(i);
                i--;
            }
        }
    }

    public void removeSize() {

        int index1 = file.getHeaderIndex(uniqueCombination[0]);
        int index2 = file.getHeaderIndex(uniqueCombination[1]);

        for (int i = 0; i < optiData.size(); i++) {
            if (optiData.get(i)[index1].length() != 17) {
            	System.out.println(optiData.get(i)[index1]);
                optiData.remove(i);
                i--;
            }

        }
    }

    public void setoptiData1() {
        int index1 = file.getHeaderIndex(uniqueCombination[0]);
        int index2 = file.getHeaderIndex(uniqueCombination[1]);
        for (int i = 0; i < optiData.size(); i++) {
            if (optiData1.get(i)[index1].length() > 15) {
                optiData1.remove(i);
            }

            if (!isNum(optiData1.get(i)[index1]) && i > 0) {
                optiData1.remove(i);
            }
        }
    }

    public ArrayList<String[]> getOptiData() {
        return optiData;
    }

    public static boolean isNum(String strNum) {
        strNum=strNum.replaceAll("\"", "");
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        } catch (NumberFormatException e) {
            ret = false;
        }
        catch (Exception e) {
        	System.out.println(e.toString());
            ret = false;
        }
        
        
    }

    public static void main(String[] args) throws IOException {

        String filepath = "F:\\selenium\\CSVFileVerification\\test1.txt";
        String resultFileName = "F:\\seleniumtool\\csv\\Test_new_r.csv";
       
        
        RemoveEntryFromCSV r = new RemoveEntryFromCSV(filepath,0);
        
        BufferedWriter out = new BufferedWriter(new FileWriter(resultFileName));
        
//Add this to write a string to a file
//
        try {
            ArrayList<String[]> data = r.getOptiData();
            for (String[] row : data) {
                String line = "";
                for (int i = 0; i < row.length; i++) {
                    if (line.equals("")) {
                        line = row[i];
                       
                    } else {
                        line = line + r.seperator.substring(1) + row[i];

                    }
                }
                out.write(line + "\r\n");
            }
        } catch (IOException e) {
            System.out.println("Exception ");

        } finally {
            out.close();
        }
        
        System.out.println(filepath);
    }

}
