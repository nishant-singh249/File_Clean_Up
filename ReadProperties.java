/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfileverification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishant.singh
 */
public class ReadProperties {
    
    public Properties  getPropertiesObject()
    {  
        Properties p=new Properties();
        try {
            FileReader reader=new FileReader("resources\\config.properties");
            p.load(reader); 
        } catch (FileNotFoundException ex) {
            System.out.println("Properties is not found at locattion src\\resources\\config.properties");
        } catch (IOException ex) {
            System.out.println("Properties file is not in proper format");
        }
       return p;
    }
    /*
    public static void main(String[] args) {
       ReadProperties r=new ReadProperties(); 
        System.out.println(r.getPropertiesObject().getProperty("user"));
    }*/
}
