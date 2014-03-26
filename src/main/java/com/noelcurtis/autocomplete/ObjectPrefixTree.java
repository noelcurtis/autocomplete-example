package com.noelcurtis.autocomplete;

import java.util.HashSet;
import java.util.Set;

public class ObjectPrefixTree<T>
{
    private PrefixTreeNode<T> root;

    public ObjectPrefixTree(T rootNodeValue)
    {
        root = new PrefixTreeNode<T>(rootNodeValue); // root node is has empty value as data
    }

    /**
     * Inserts values into the prefix tree
     *
     * @param values
     */
    public void insert(T[] values)
    {
        PrefixTreeNode<T> current = root;
        if (values != null)
        {
            if (values.length == 0)
            {
                current.setEndMarker(true); // empty value
            }
            for (int i = 0; i < values.length; i++)
            {
                PrefixTreeNode<T> child = current.findChild(values[i]);
                if (child != null)
                {
                    current = child;
                } else
                {
                    current = current.addChild(values[i]);
                }
                if (i == values.length - 1)
                {
                    if (!current.isEndMarker())
                    {
                        current.setEndMarker(true);
                    }
                }
            }
        } else
        {
            System.out.println("Nothing to add");
        }
    }

    /**
     * Finds all words in the prefix tree
     *
     * @return
     */
    public Set<String> findAllWords()
    {
        return findWords(this.root, new HashSet<String>(), this.root.getValue().toString());
    }

    /**
     * Finds words for a prefix in the tree
     *
     * @param values
     * @return
     */
    public Set<String> findWordsForPrefix(T[] values)
    {
        PrefixTreeNode<T> current = root;
        String currentString = "";
        for (int i = 0; i < values.length; i++)
        {
            if (current.findChild(values[i]) == null)
            {
                return new HashSet<String>(); // nothing is found
            } else
            {
                current = current.findChild(values[i]);
            }
            currentString += values[i].toString();
        }

        if (current.isEndMarker())
        {
            Set<String> result = new HashSet<String>();
            result.add(currentString);
            return result;
        } else
        {
            return findWords(current, new HashSet<String>(), currentString);
        }
    }

    /**
     * Finds words for a prefix from the current node down through all its sub trees
     *
     * @param node
     * @param words
     * @param currentWord
     * @return
     */
    private Set<String> findWords(PrefixTreeNode<T> node, Set<String> words, String currentWord)
    {
        PrefixTreeNode<T> current = node;
        for (PrefixTreeNode<T> child : current.children)
        {
            String newWord = new StringBuilder().append(currentWord).append(child.getValue().toString()).toString();
            if (child.isEndMarker())
            {
                words.add(newWord.trim());
            }
            findWords(child, words, newWord);
        }
        return words;
    }
}
