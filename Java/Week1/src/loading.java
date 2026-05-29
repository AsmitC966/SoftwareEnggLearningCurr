/*
    to load the csv files and prepare the data to be used in different functions


    csv files are kinda like tables with each column having ',' separator
    so each column can be stores as a row with an array and each array will represent each row so we have a 
    2D array case I think.


    but I don't know what size to give array...maybe go with a huge size just in case
    or before storing in the array
    or 
    I could read the file to find the size? then another one to write up the contents



*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class loading
{
    String[][] read(String path)
    {
        //1st to read the file and get the input size
        int count=0;//row count
        try
        {

            Scanner sc=new Scanner(new File(path));
            //Scanner object to read from File via File object and not System.in

            if(sc.hasNextLine()) sc.nextLine();//skipping the column header


            while(sc.hasNextLine())
            {
                String row=sc.nextLine();
                if(!row.isEmpty())
                    count++;
            }
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
            return new String[0][0];//empty dataset basically, not null
        }

        
        String[][] output=new String[count][];
        //just count, no column number because there might be 
        // missing value so  no hardcoding column count

        //now to fill the array
        int i=0;

        try
        {
            Scanner sc=new Scanner(new File(path));
            if(sc.hasNextLine()) sc.nextLine();//skipping the column header
            while(sc.hasNextLine())
            {
                String row=sc.nextLine();
                if(!row.isEmpty())
                {
                    output[i]=row.split(",");
                    i++;
                }
            }
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File Not Found");
        }
        return output;
    }    
}
