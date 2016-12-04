package com.google.engedu.ghost;
// change this package name
import java.util.ArrayList;

/**
 * Created by sivagami on 21/9/16.
 */
public class TernarySearchTree {

    private TSTNode root;
    private ArrayList<String> a1;

    public TernarySearchTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // call this function to insert word from dictionary
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);

    }

    public TSTNode insert(TSTNode r, char[] word, int ptr) {
        if (r == null) r = new TSTNode(word[ptr]);

        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else
                r.isEnd = true;

        }
        return r;
    }

    // this function to check if the word entered by the user is a valid word
    public boolean search(String word) {

        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TSTNode r, char[] word, int ptr) {
        if (r == null) return false;

        if (word[ptr] < r.data)
            return search(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return search(r.right, word, ptr);
        else {
            if (r.isEnd && ptr == word.length - 1)
                return true;
            else if (ptr == word.length - 1)
                return false;
            else
                return search(r.middle, word, ptr + 1);


        }

    }

}
