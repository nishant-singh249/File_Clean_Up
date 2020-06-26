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

/**
 *
 * @author Nishant.singh
 */
public class ManiVerification {
    public static void main(String[] args) throws IOException {
        
        String filePath1=args[0];//"E:\\r\\DailyDataFeed_6_6969_20190228.txt";
        String filePath2=args[1];//"E:\\r\\DailyDataFeed_6_6969_201902281.txt";
       BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));

//Add this to write a string to a file
//
try {

    out.write("aString\nthis is a\nttest");  //Replace with the string 
         CompareFile c=new CompareFile(filePath1,filePath2);
        
        ArrayList <String>HeaderCompareResult=c.getCompareResult();
       System.out.println(HeaderCompareResult.size());
         for(String x:HeaderCompareResult)
        {
            System.out.println(x);
            out.write(x+"\r\n");
        }                                     //you are trying to write
}
catch (IOException e)
{
    System.out.println("Exception ");

}
finally
{
    out.close();
}
       
    }
            
}
