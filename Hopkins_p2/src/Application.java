import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class DuplicateCounter{

    private HashMap<String, Integer> wordCounter = new HashMap<String, Integer>();

    public void count(File dataFile){

        try{

            FileInputStream fIN = new FileInputStream(dataFile);
            Scanner scI = new Scanner(fIN);

            String cur = "";

            while(scI.hasNextLine()){

                cur = scI.nextLine();

                if(wordCounter.containsKey(cur) == false){

                    wordCounter.put(cur, 1);

                }else{

                    wordCounter.put(cur, (wordCounter.get(cur) + 1));

                }

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
            PrintWriter dO = new PrintWriter(new BufferedOutputStream(out));

            for(Map.Entry<String, Integer> n : wordCounter.entrySet()){

                dO.println(n.getKey() + " " + n.getValue());

            }

            dO.flush();
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
        DuplicateCounter duplicateCounter;
        duplicateCounter = new DuplicateCounter();

        duplicateCounter.count(new File("problem2.txt"));
        duplicateCounter.write(new File("unique_words_count.txt"));

    }

}
