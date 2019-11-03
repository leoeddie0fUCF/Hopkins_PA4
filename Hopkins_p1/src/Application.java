import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

class DuplicateRemover{

    private HashSet<String> uniqueWords = new HashSet<String>();
    private HashSet<String> ignore = new HashSet<String>();

    public void remove(File dataFile) throws IOException {

        try{

            FileInputStream fIN = new FileInputStream(dataFile);
            Scanner scI = new Scanner(fIN);

            String cur = "";

            while (scI.hasNextLine()){

                cur = scI.nextLine();

                if(uniqueWords.contains(cur) == false){

                    uniqueWords.add(cur);

                }else if(ignore.contains(cur) == false){

                    ignore.add(cur);

                }

            }

            for(String s : ignore){

                uniqueWords.remove(s);

            }

            fIN.close();

        }catch(IOException e){

            System.out.println("I/O Exception encountered. Stopping process.");
            System.out.println(e.getMessage());

        }
    }

    public void write(File dataFile){

        try{

            FileOutputStream out = new FileOutputStream(dataFile);
            DataOutputStream dO = new DataOutputStream(new BufferedOutputStream(out));

            for(String t : uniqueWords){

                dO.writeBytes(t);
                dO.writeBytes("\n");

            }

            dO.close();
            out.close();

        }catch(IOException e){

            System.out.println("I/O Exception encountered. Stopping process.");
            System.out.println(e.getMessage());

        }

    }

}

public class Application {

    public static void main(String[] args){
        DuplicateRemover duplicateRemover;
        duplicateRemover = new DuplicateRemover();

        try {
            duplicateRemover.remove(new File("problem1.txt"));
            duplicateRemover.write(new File("unique_words.txt"));
        } catch (IOException e) {
            System.out.println("I/O Exception encountered. Stopping Program.");
            e.printStackTrace();
        }

    }

}
