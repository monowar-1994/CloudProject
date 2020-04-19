package IndexBuild;

import java.io.*;
import java.util.*;

public class PrefixTree {
    public PrefixTreeNode root = null;

    public PrefixTree(){
        root = new PrefixTreeNode('#',0);
    }

    public void insertInPrefixTree(String s){
        char[] array = s.toCharArray();
        PrefixTreeNode curr = root;
        for(int k=0;k<array.length;k++){
            if(array[k] == 'A'){
                if(curr.nextPointers[0] == null){
                    curr.nextPointers[0] = new PrefixTreeNode('A',k+1);
                    curr = curr.nextPointers[0];
                }
                else{
                    curr = curr.nextPointers[0];
                }
            }
            else if(array[k] == 'T'){
                if(curr.nextPointers[1] == null){
                    curr.nextPointers[1] = new PrefixTreeNode('T',k+1);
                    curr = curr.nextPointers[1];
                }
                else{
                    curr = curr.nextPointers[1];
                }
            }
            else if(array[k] == 'C'){
                if(curr.nextPointers[2] == null){
                    curr.nextPointers[2] = new PrefixTreeNode('C',k+1);
                    curr = curr.nextPointers[2];
                }
                else{
                    curr = curr.nextPointers[2];
                }
            }
            else if(array[k] == 'G'){
                if(curr.nextPointers[3] == null){
                    curr.nextPointers[3] = new PrefixTreeNode('G',k+1);
                    curr = curr.nextPointers[3];
                }
                else{
                    curr = curr.nextPointers[3];
                }
            }
            else{
                System.out.println("Error found while parsing string");
                break;
            }
        }
    }

    public boolean searchInPrefixTree(String s){
        boolean res = true;
        char[] array = s.toCharArray();
        PrefixTreeNode curr = root;
        for(int k=0;k<array.length;k++){
            if(array[k] == 'A'){
                if(curr.nextPointers[0] == null){
                    res = false;
                    break;
                }
                else{
                    curr = curr.nextPointers[0];
                }
            }
            else if(array[k] == 'T'){
                if(curr.nextPointers[1] == null){
                    res = false;

                    break;
                }
                else{
                    curr = curr.nextPointers[1];

                }
            }
            else if(array[k] == 'C'){
                if(curr.nextPointers[2] == null){
                    res = false;
                    break;
                }
                else{
                    curr = curr.nextPointers[2];

                }
            }
            else if(array[k] == 'G'){
                if(curr.nextPointers[3] == null){
                    res = false;
                    break;
                }
                else{
                    curr = curr.nextPointers[3];
                }
            }
            else{
                System.out.println("Error found while parsing string");
                res = false;
                break;
            }
        }
        return res;
    }
}

