
 * This code is the implementation of the HeapPQueue class.
 * The HeapPQueue class implements the PQueue interface.
 * The HeapPQueue class has three private fields:
 * heap: an array of integers, which stores the vertices in the heap.
 * index: an array of integers, which stores the index of each vertex in the heap.
 * key: an array of integers, which stores the key value of each vertex.
 * The HeapPQueue class has one private field:
 * size: an integer, which stores the number of vertices in the heap.
 * The HeapPQueue class has one constructor:
 * public HeapPQueue(int capacity):
 * The constructor initializes the heap, index and key arrays, and sets the size to 0.
 * The HeapPQueue class has four methods:
 * public void insert(int v, int k):
 * The method inserts a vertex v with key value k into the heap.
 * public int deleteMin():
 * The method deletes the vertex with the minimum key value from the heap and returns the vertex.
 * public void decrease(int v, int k):
 * The method decreases the key value of vertex v to k.
 * public boolean contains(int v):
 * The method returns true if the heap contains vertex v, and returns false otherwise.
 * public boolean isEmpty():
 * The method returns true if the heap is empty, and returns false otherwise.
 