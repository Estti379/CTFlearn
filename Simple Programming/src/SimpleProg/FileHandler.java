package SimpleProg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class FileHandler {
    private File textFile = null;
    private Scanner myReader = null;

    protected FileHandler(){
        //empty
    }

    protected void openFile(String myFile){
        textFile = new File(myFile);
        try{
            myReader = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    protected String getNextLine(){
        if (myReader.hasNextLine()) {
            return myReader.nextLine();
        } else {
            return null;
        }
    }

}
