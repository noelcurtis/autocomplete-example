package com.noelcurtis.autocomplete;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DishRanker
{
    private final String _filePath;
    private Map<String, Integer> _dishCount;

    public DishRanker(String filePath)
    {
        _filePath = filePath;
    }

    public Map<String, Integer> getDishCount()
    {
        return _dishCount;
    }

    public void processfile() throws Exception
    {
        //long currentTime = System.currentTimeMillis();
        _dishCount = new HashMap<String, Integer>();
        // Get the file URL
        File file = new File(_filePath);
        if (!file.exists())
        {
            throw new Exception("File not found at path:" + _filePath);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        // Read lines and process then one at a time
        for (String line; (line = br.readLine()) != null; )
        {
            processLine(line);
        }
        br.close();
        //System.out.println("Processing file took: " + (System.currentTimeMillis() - currentTime) + "ms");
    }

    public void processLine(String line)
    {
        if (Strings.isNullOrEmpty(line)) return;

        String[] words = line.split(",");
        // create a list of paired words
        for (String word : words)
        {
            String trimmed = word.trim().toLowerCase();
            if (_dishCount.containsKey(trimmed))
            {
                _dishCount.put(trimmed, _dishCount.get(trimmed) + 1);
            } else
            {
                _dishCount.put(trimmed, 1);
            }
        }
    }

    public void printOutput()
    {
        for (Map.Entry<String, Integer> e : _dishCount.entrySet())
        {
            System.out.println("{word: " + e.getKey() + ", popularity:" + e.getValue() + "}");
        }
    }
}
