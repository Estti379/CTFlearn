package SimpleProg;

public class LineDecoder {

    private String filePath = null;
    private FileHandler fileHandler = null;


    public LineDecoder(String filePath){
        this.filePath = filePath;
    }

    public void start(){
        initializeFile();
        counter();
    }

    private void initializeFile(){
        fileHandler = new FileHandler();
        fileHandler.openFile(filePath);
    }

    private void counter(){
        int nbValidLines = 0;
        String data = fileHandler.getNextLine();
        while(data != null){
            if(validData(data)){
                nbValidLines++;
            }
            data = fileHandler.getNextLine();
        }

        System.out.println("Number of valid lines is: " + nbValidLines + " !");

    }


    private boolean validData(String data){
        if( data == null){
            return false;
        } else {
            return (countOccurrences(data, '0') % 3 == 0) || (countOccurrences(data, '1') % 2 == 0) ;
        }

    }

    private int countOccurrences(String data, char a){
        int occurrences = 0;
        for (int i = 0; i < data.length(); i++) {
            if(data.charAt(i) == a){
                occurrences++;
            }
        }

        return occurrences;
    }

}
