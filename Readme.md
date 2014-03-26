## Usage
*   You can run the program using `java -jar autocomplete.jar seeddata.txt`
*   The jar can be found in the build folder `/build/autocomplete.jar`
*   If you would like to use a file of your own the file should be formatted as follows
    -   `dish1,dish2,dish3\ndish4,dish5,dish6,dish7\nEOF`

## Build
*   The project is built with Maven using `mvn clean install`

## Method
*   The Initialization phase has 2 steps
    -   __Counting dishes__
    -   The file is parsed and a HashTable is used to maintain a count of unique dishes, this count is used later to rank output results.
    -   __Building Prefix tree__
    -   After the HashTable is built all unque words are added onto a prefix tree
    -   The prefix tree is used to lookup possible words when a prefix is input
    -   All sub-trees of a prefix are returned as results
*   Ranking Results
    -   Once a possible set of words is returned from the prefix tree it is sorted based on its _count_ from the HashTable
    -   This was the most 'popular' dish is returned at the top of the list.

