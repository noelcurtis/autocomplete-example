package com.noelcurtis.autocomplete;

import java.util.ArrayList;

public class PrefixTreeNode<T>
{
    private T _value;
    private boolean _endMarker;
    public ArrayList<PrefixTreeNode<T>> children;

    public PrefixTreeNode(T value)
    {
        _value = value;
        _endMarker = false;
        this.children = new ArrayList<PrefixTreeNode<T>>();
    }

    public PrefixTreeNode<T> findChild(T value)
    {
        if (this.children != null)
        {
            for (PrefixTreeNode<T> n : this.children)
            {
                if (n.getValue().equals(value))
                {
                    return n;
                }
            }
        }
        return null;
    }

    public T getValue()
    {
        return _value;
    }

    public void setEndMarker(boolean endMarker)
    {
        _endMarker = endMarker;
    }

    public boolean isEndMarker()
    {
        return _endMarker;
    }

    public PrefixTreeNode<T> addChild(T value)
    {
        PrefixTreeNode<T> n = new PrefixTreeNode<T>(value);
        this.children.add(n);
        return n;
    }

}
