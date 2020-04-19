package IndexBuild;

import java.util.*;
import java.io.*;
import java.math.*;

public class EncryptedPrefixTreeNode{
    public BigInteger val;
    public EncryptedPrefixTreeNode[] nextPointers = null;

    public EncryptedPrefixTreeNode(BigInteger encryptedVal){
        val = encryptedVal;
        nextPointers = new EncryptedPrefixTreeNode[4];
        Arrays.fill(nextPointers,null);
    }
}