package IndexBuild;

import de.henku.jpaillier.KeyPair;
import de.henku.jpaillier.KeyPairBuilder;
import de.henku.jpaillier.PublicKey;

import java.math.*;
import java.util.LinkedList;
import java.util.Queue;

public class EncryptedPrefixTree{
    public EncryptedPrefixTreeNode root = null;
    private KeyPair kp = null;
    public PublicKey pubKey = null;


    public EncryptedPrefixTree(){
        KeyPairBuilder keygen = new KeyPairBuilder();
        kp = keygen.generateKeyPair();
        pubKey = kp.getPublicKey();
        root = new EncryptedPrefixTreeNode(new BigInteger(String.valueOf(0)));
    }


    public void buildEncryptedTreeFromPrefixTree(PrefixTree tree){

        long start = System.currentTimeMillis();

        PrefixTreeNode curr = tree.root;
        PrefixTreeNode nullNode = new PrefixTreeNode('#',-1);
        Queue<PrefixTreeNode> levelwiseQueue0 = new LinkedList<PrefixTreeNode>();
        Queue<PrefixTreeNode> levelwiseQueue1 = new LinkedList<PrefixTreeNode>();

        // Cloning the index tree in a queue so that the encrypted tree can be created

        if(isNextEmpty(curr)==false){
            for(int k=0;k<4;k++){
                if(curr.nextPointers[k] != null){
                    levelwiseQueue0.add(curr.nextPointers[k]);
                    levelwiseQueue1.add(curr.nextPointers[k]);
                }
                else {
                    levelwiseQueue1.add(nullNode);
                }
            }
        }

        while(levelwiseQueue0.isEmpty()==false){
            curr = levelwiseQueue0.poll();
            if(isNextEmpty(curr)==false){
                for(int k=0;k<4; k++){
                    if(curr.nextPointers[k]  != null){
                        levelwiseQueue0.add(curr.nextPointers[k]);
                        levelwiseQueue1.add(curr.nextPointers[k]);
                    }
                    else {
                        levelwiseQueue1.add(nullNode);
                    }
                }
            }
        }

        // We will use the levelwise queue 1 to construct the encrypted index tree

        levelwiseQueue0.clear();
        Queue<EncryptedPrefixTreeNode> encryptedQueue = new LinkedList<EncryptedPrefixTreeNode>();

        for(int k=0;k<4;k++){
            PrefixTreeNode temp = levelwiseQueue1.remove();

            if(temp != null){
                if(temp.level != -1){
                    levelwiseQueue0.add(temp);
                    root.nextPointers[k] = encryptNode(temp, pubKey); // The first layer of the encrypted index tree.
                    encryptedQueue.add(root.nextPointers[k]);
                    //System.out.println("Adding first layer of encrypted nodes.: "+k+" value is: "+temp.nodeValue);
                }else{
                    //System.out.println("Setting null. "+k);
                    root.nextPointers[k] = null;
                }
            }
        }

        System.out.println(encryptedQueue.peek().val.toString());

        // Start building the encrypted tree
        // System.out.println("q0: "+levelwiseQueue0.size());
        // System.out.println("qe: "+encryptedQueue.size());

        curr = null;
        EncryptedPrefixTreeNode en_curr = null;
        int index = 0;

        while(levelwiseQueue0.isEmpty()==false){
            curr = levelwiseQueue0.poll();
            en_curr = encryptedQueue.poll();
            System.out.print(index + " ");
            if(index%20==0){
                System.out.print("\n");
            }
            index++;

            //System.out.println("Iterating");

            for(int k=0;k<4;k++){
                if(levelwiseQueue1.isEmpty()){
                    break;
                }
                PrefixTreeNode temp = levelwiseQueue1.poll();
                if(temp != null){
                    if(temp.level != -1){
                        levelwiseQueue0.add(temp);
                        en_curr.nextPointers[k] = encryptNode(temp, pubKey); // The subsequent layer of the encrypted index tree.
                        encryptedQueue.add(en_curr.nextPointers[k]);
                    }else{
                        en_curr.nextPointers[k] = null;
                    }
                }
            }

        }
        long end = System.currentTimeMillis();

        System.out.print("\n\nTotal time elapsed to create the encrypted index tree: "+(end-start));
    }

    public EncryptedPrefixTreeNode encryptNode(PrefixTreeNode node, PublicKey key){
        int val = node.nodeValue;
        BigInteger biVal = new BigInteger(String.valueOf(val));
        BigInteger enVal = key.encrypt(biVal);
        EncryptedPrefixTreeNode enNode = new EncryptedPrefixTreeNode(enVal);
        return enNode;
    }

    public boolean isNextEmpty(PrefixTreeNode node){
        if(node.nextPointers[0] == null && node.nextPointers[1] == null && node.nextPointers[2] == null
            && node.nextPointers[3] == null){
            return true;
        }
        return false;
    }

    public boolean isNextEmpty(EncryptedPrefixTreeNode node){
        if(node.nextPointers[0] == null && node.nextPointers[1] == null && node.nextPointers[2] == null
                && node.nextPointers[3] == null){
            return true;
        }
        return false;
    }

    public void printEncryptedPrefixTree(){
        EncryptedPrefixTreeNode node = root;
        Queue<EncryptedPrefixTreeNode> q = new LinkedList<EncryptedPrefixTreeNode>();
        for(int i=0;i<4;i++){
            if(node.nextPointers[i] != null){
                q.add(node.nextPointers[i]);
            }
        }
        while(q.isEmpty()==false){
            node = q.poll();
            System.out.println(node.val.toString());
            System.out.println();
            if(node != null && !isNextEmpty(node)){
                for(int k=0;k<4;k++){
                    if(node.nextPointers[k] != null){
                        q.add(node.nextPointers[k]);
                    }
                }
            }
        }
    }
}