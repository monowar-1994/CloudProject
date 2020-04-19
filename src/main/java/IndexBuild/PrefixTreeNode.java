package IndexBuild;

import java.io.*;
import java.util.*;

public class PrefixTreeNode {
    public char nodeValue;
    public PrefixTreeNode[] nextPointers;
    public int level;

    public PrefixTreeNode(char val, int x){
        nodeValue = val;
        level = x;
        nextPointers = new PrefixTreeNode[4]; // Represents A,T,G,C
        Arrays.fill(nextPointers,null);
    }
}