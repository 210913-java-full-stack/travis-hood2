package utils.datastructures;

public interface MyArrayListInterface <E> {
    //returning the size of the collection. we'll need to maintain some int with the number of elements
    int size();

    //
    int contains(E e);

    void add(E e);

    void add(E e, int index);

    E get(int index);

    void remove(int index);

    void clear();

}

