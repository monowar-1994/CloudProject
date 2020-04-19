package IndexBuild;

import java.io.*;

public class DataGenerator {
    public String filepath = "data.txt";
    public static BufferedWriter bw = null;

    public DataGenerator(){
        try{
            bw = new BufferedWriter(new FileWriter(filepath));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    private final String ALPHA_NUMERIC_STRING = "ATCG";

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        builder.append('\n');
        return builder.toString();
    }

    public void generate(int count){
        while(count-- != 0){
            try{
                bw.write(randomAlphaNumeric(32));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        try{
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
