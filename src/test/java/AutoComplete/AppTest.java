package AutoComplete;

import com.noelcurtis.autocomplete.*;
import org.junit.Test;

import java.net.URL;
import java.util.*;

public class AppTest
{
    @Test
    public void testDishRanker() throws Exception
    {
        URL url = App.class.getClassLoader().getResource("seeddata.txt");
        String filePath = url.getPath();
        DishRanker dishRanker = new DishRanker(filePath);
        dishRanker.processfile();

        Map<String, Integer> results = dishRanker.getDishCount();
        assert (results.get("chargrilled pork") == 2);
    }


    @Test
    public void testPrefixTree()
    {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.insert("beef ribs");
        prefixTree.insert("chicken");
        assert(prefixTree.findAllWords().size() == 2);
    }

    @Test
    public void testFindAllWords()
    {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.insert("beef ribs");
        prefixTree.insert("chicken");
        //System.out.println(prefixTree.findAllWords());
    }

    @Test
    public void testFindAllWordsFile() throws Exception
    {
        URL url = App.class.getClassLoader().getResource("seeddata.txt");
        String filePath = url.getPath();
        DishRanker dishRanker = new DishRanker(filePath);
        dishRanker.processfile();

        Map<String, Integer> rankedDishes = dishRanker.getDishCount();
        PrefixTree prefixTree = new PrefixTree();
        for (String s : rankedDishes.keySet())
        {
            prefixTree.insert(s);
        }
        Set<String> words = prefixTree.findAllWords();
        //System.out.println("All Words: "+ words);

        List<WeightedDish> sortedDishes = new LinkedList<WeightedDish>();
        for (String word : words.toArray(new String[]{}))
        {
            WeightedDish weightedDish = new WeightedDish(word, rankedDishes.get(word));
            sortedDishes.add(weightedDish);
        }
        Collections.sort(sortedDishes);
        //System.out.println("All Words: "+ sortedDishes);
    }

    @Test
    public void testFindWordsForPrefix() throws Exception
    {
        URL url = App.class.getClassLoader().getResource("seeddata.txt");
        String filePath = url.getPath();
        DishRanker dishRanker = new DishRanker(filePath);
        dishRanker.processfile();

        Map<String, Integer> rankedDishes = dishRanker.getDishCount();
        PrefixTree prefixTree = new PrefixTree();
        for (String s : rankedDishes.keySet())
        {
            prefixTree.insert(s);
        }
        Set<String> words = prefixTree.findWordsForPrefix("ch");
        assert (words.size() == 4);
        //System.out.println("All Words: "+ words);

        List<WeightedDish> sortedDishes = new LinkedList<WeightedDish>();
        for (String word : words.toArray(new String[]{}))
        {
            WeightedDish weightedDish = new WeightedDish(word, rankedDishes.get(word));
            sortedDishes.add(weightedDish);
        }
        Collections.sort(sortedDishes);
        assert (sortedDishes.size() == 4);
        assert (sortedDishes.get(0).getWeight() == 9);
        //System.out.println("All Words: "+ sortedDishes);
    }

    @Test
    public void testSearchComplete() throws Exception
    {
        URL url = App.class.getClassLoader().getResource("seeddata.txt");
        String filePath = url.getPath();
        SearchComplete searchComplete = new SearchComplete(filePath);
        searchComplete.initialize();
        WeightedDish[] sortedDishes = searchComplete.getPossibleDishes("ch");
        assert (sortedDishes.length == 4);
        assert (sortedDishes[0].getWeight() == 9);
    }

}
