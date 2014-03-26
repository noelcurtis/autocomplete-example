package com.noelcurtis.autocomplete;

import java.util.Set;

public class PrefixTree
{
    private ObjectPrefixTree<Character> prefixTree;

    /**
     * Use to initialize a PrefixTree with empty root
     */
    public PrefixTree()
    {
        prefixTree = new ObjectPrefixTree<Character>(' ');
    }

    /**
     * Insert a String into the prefix tree
     *
     * @param s
     */
    public void insert(String s)
    {
        prefixTree.insert(toArray(s));
    }

    private Character[] toArray(String s)
    {
        Character[] cArray = new Character[s.length()];
        for (int i = 0; i < cArray.length; i++)
        {
            cArray[i] = s.charAt(i);
        }
        return cArray;
    }

    /**
     * Find all the words in the prefix tree
     *
     * @return
     */
    public Set<String> findAllWords()
    {
        return prefixTree.findAllWords();
    }

    /**
     * Find all words which have a prefix s
     *
     * @param s
     * @return
     */
    public Set<String> findWordsForPrefix(String s)
    {
        return prefixTree.findWordsForPrefix(toArray(s));
    }
}
