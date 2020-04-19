package IndexBuild;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        PrefixTree index_tree = new PrefixTree();
        String filepath = "data.txt";
        BufferedReader br = null;
        String line = "";
        try{
            br = new BufferedReader(new FileReader(filepath));
            line = br.readLine();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        while(line != null){
            line = line.trim();
            index_tree.insertInPrefixTree(line);
            try{
                line = br.readLine();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        System.out.println("Prefix Tree insertion ended. Encryption begins.");

        EncryptedPrefixTree eTree = new EncryptedPrefixTree();

        eTree.buildEncryptedTreeFromPrefixTree(index_tree);

    }
}
