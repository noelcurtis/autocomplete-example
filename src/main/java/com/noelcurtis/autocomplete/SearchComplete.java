package com.noelcurtis.autocomplete;

import java.util.*;

public class SearchComplete
{
    private Map<String, Integer> _dishCount;
    private PrefixTree _prefixTree;
    private String _filePath;

    public SearchComplete(String filePath)
    {
        _filePath = filePath;
    }

    /**
     * Process the flat file and initialize
     *
     * @throws Exception
     */
    public void initialize() throws Exception
    {
        // Count the file and rank dishes
        DishRanker dishRanker = new DishRanker(_filePath);
        dishRanker.processfile();
        dishRanker.printOutput();
        _dishCount = dishRanker.getDishCount();
        // Insert all the dishes onto a prefix tree for search
        _prefixTree = new PrefixTree();
        for (String s : _dishCount.keySet())
        {
            _prefixTree.insert(s);
        }
    }

    /**
     * Get all possible dishes for a prefix
     *
     * @param prefix
     * @return
     */
    public WeightedDish[] getPossibleDishes(String prefix)
    {
        Set<String> words = _prefixTree.findWordsForPrefix(prefix);
        List<WeightedDish> sortedDishes = new LinkedList<WeightedDish>();
        for (String word : words.toArray(new String[]{}))
        {
            WeightedDish weightedDish;
            if (!_dishCount.containsKey(word))
            {
                weightedDish = new WeightedDish(word, 0);
            } else
            {
                weightedDish = new WeightedDish(word, _dishCount.get(word));
            }
            sortedDishes.add(weightedDish);
        }
        // sort the dishes
        Collections.sort(sortedDishes);
        return sortedDishes.toArray(new WeightedDish[]{});
    }
}
