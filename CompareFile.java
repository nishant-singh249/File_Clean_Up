/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfileverification;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Nishant.singh
 */
public class CompareFile {

    Properties Property;
    InputCSVFile file1;
    InputCSVFile file2;
    String seperator;
    String uniqueColumn;
    ArrayList<String> ignoreAttribute;
    ArrayList<String> compareResult;
    ArrayList<String> headerCompareResult;

    public CompareFile(String file1, String file2) {
        setProperty();
        setSeperator();
        setIgnoreAttribute();
        setUniqueColumn();
        this.file1 = new InputCSVFile(file1, ignoreAttribute, seperator);
        this.file2 = new InputCSVFile(file2, ignoreAttribute, seperator);
        //setHeaderCompareResult();
        setCompareResult();
    }

    public void setProperty() {
        ReadProperties read = new ReadProperties();
        this.Property = read.getPropertiesObject();
    }

    public void setSeperator() {
        this.seperator = Property.getProperty("seperator");
    }

    public void setUniqueColumn() {
        this.uniqueColumn = Property.getProperty("uniqueColumn");
    }

    public void setIgnoreAttribute() {
        String[] data = Property.get("ignoreAttribute").toString().split(",");
        ArrayList<String> ignoreAttribute = new ArrayList<String>();
        for (String x : data) {
            ignoreAttribute.add(x);
        }
        this.ignoreAttribute = ignoreAttribute;
    }

    public void setCompareResult() {
       ArrayList<String> compareResult = new ArrayList<>();
        
        ArrayList<String[]> FullDataWithoutHeader1 = file1.getFullData();
        ArrayList<String[]> FullDataWithoutHeader2 = file2.getFullData();

        int UniqueColumnIndexfile1 = file1.getHeaderIndex(uniqueColumn);
        int UniqueColumnIndexfile2 = file2.getHeaderIndex(uniqueColumn);

        String[] file1Header = file1.getheader();
        String[] file2Header = file2.getheader();

        for (String[] FullDataWithoutHeader1_I : FullDataWithoutHeader1) {
            
            int peropertyChnage = 0;
            for (String[] FullDataWithoutHeader2_I : FullDataWithoutHeader2) {
                if (FullDataWithoutHeader1_I[UniqueColumnIndexfile1].equals(FullDataWithoutHeader2_I[UniqueColumnIndexfile2])) {
                    peropertyChnage++;

                    for (int i = 0; i < file1Header.length; i++) {
                        int peropertyChnageInner = 0;
                        for (int j = 0; j < file2Header.length; j++) {
                            if (file1Header[i].equals(file2Header[j])) {
                                peropertyChnageInner++;
                                if (FullDataWithoutHeader1_I[i].equals(FullDataWithoutHeader2_I[j])) {

                                } else {
                                    String Message = "For client ID " + FullDataWithoutHeader1_I[UniqueColumnIndexfile1] + " and Column ->" + file1Header[i] + " "
                                            + "mismatch is found  " + FullDataWithoutHeader1_I[i] + " ," + FullDataWithoutHeader2_I[j];
                                    compareResult.add(Message);
                                }
                            }

                        }
                        if (peropertyChnageInner == 0) {
                            compareResult.add("Column ->" + file1Header[i] + " is not present in result");
                        } else if (peropertyChnageInner > 1) {
                            compareResult.add("Column ->" + file1Header[i] + " is  present more then one time in result");
                        }
                    }

                }
            }

            if (peropertyChnage == 0) {
                compareResult.add("For clientID " + FullDataWithoutHeader1_I[UniqueColumnIndexfile1] + " Data is not found");
            } else if (peropertyChnage > 1) {
                compareResult.add("For clientID " + FullDataWithoutHeader1_I[UniqueColumnIndexfile1] + " Data is found multiple times");
            }
        }

        this.compareResult = compareResult;

    }

    public ArrayList<String> getCompareResult() {
        return compareResult;
    }

    public void setHeaderCompareResult() {
        String[] file1Header = file1.getheader();
        String[] file2Header = file2.getheader();
        ArrayList<String> headerCompareResult = new ArrayList<>();
        for (int i = 0; i < file1Header.length; i++) {
            int peropertyChnage = 0;
            for (int j = 0; j < file2Header.length; j++) {
                if (file1Header[i].equals(file2Header[j])) {
                    peropertyChnage++;
                }

            }
            if (peropertyChnage == 0) {
                headerCompareResult.add("Column ->" + file1Header[i] + " is not present in result");
            } else if (peropertyChnage > 1) {
                headerCompareResult.add("Column ->" + file1Header[i] + " is  present more then one time in result");
            }
        }

        this.headerCompareResult = headerCompareResult;
    }

    public ArrayList<String> getHeaderCompareResult() {
        return headerCompareResult;
    }

}
