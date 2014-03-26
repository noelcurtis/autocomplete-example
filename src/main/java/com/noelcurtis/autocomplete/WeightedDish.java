package com.noelcurtis.autocomplete;

public class WeightedDish implements Comparable<WeightedDish>
{
    private String _name;
    private int _weight;

    public WeightedDish(String name, int weight)
    {
        _name = name;
        _weight = weight;
    }

    public String getName()
    {
        return _name;
    }

    public int getWeight()
    {
        return _weight;
    }

    @Override
    public int compareTo(WeightedDish anotherDish)
    {
        if (_weight == anotherDish.getWeight())
        {
            return 0;
        } else
        {
            return _weight - anotherDish.getWeight() > 0 ? -1 : 1;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof WeightedDish))
        {
            return false;
        } else if (((WeightedDish) o).getName().equals(_name) && ((WeightedDish) o).getWeight() == _weight)
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return _name.hashCode() + (31 * _weight);
    }

    @Override
    public String toString()
    {
        return _name + ":" + _weight;
    }
}
