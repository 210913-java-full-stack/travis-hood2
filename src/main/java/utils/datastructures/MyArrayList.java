package utils.datastructures;



public class MyArrayList<E> implements MyArrayListInterface<E> {
    private int size;
    Object[] arlist = new Object[2];// instantiate arlist as core array with size of 2

    private void expandArList (){
        Object[] tempArList = new Object[(size*2)];//create temporary array & set size to double our current array list size
        for (int i = 0; i < size; i++){//for i less than number of obj. in the array we want to copy our old array data over to our temp array
            tempArList[i] = arlist[i];//copy data from old to new at each index
        }
        arlist = tempArList;//tempArList is assigned to arlist effectively finishing the resize and copy
    }

    @Override //get size of array
    public int size() {
        return size;
    }

    @Override
    public int contains(E e) {
        for (int i = 0; i < size; i++){//for loop utilizing index compared to size of array
            if (arlist[i].equals(e)){ //if arlist @ index i is equiv. to obj. return that index
                return i;
            }
        }
        return (-1);//return an int of -1 if not in index
    }

    @Override
    public void add(E e) {
        int sizeOf = arlist.length;//set field to length of our array list 'arlist'

        if (size == sizeOf){//if length of our array is equal to our size we need to grow our array list
            expandArList();
        }

        arlist[size] = e;//arlist adds obj. to the end of the array (@ index = size)
        size++;
    }

    @Override
    public void add(E e, int index) {
        int sizeOf = arlist.length;

        if (index > size || index < 0 ){//if out of index, throw exception
            throw new IndexOutOfBoundsException("Try again, you messed up");
        }

        if (size == sizeOf){ //if loop checks for length equiv.
            expandArList();
        }

        Object[] arr = new Object[arlist.length];//create new array for addition via insertion

        arr[index] = e;//array at index of 'index' is set to generic obj.

        for(int i = 0; i < index; i++){//for loop to copy data to new array list to the right of 'index'
            arr[i] = arlist[i];
        }

        for (int i = index+1; i <= size; i++){//same as line75(above) at opposite side of index
            arr[i] = arlist[i-1];
        }
        arlist = arr;//old array is set to ref of 'arr'
        size++;//increment size
    }

    @Override
    public E get(int index) {
        if(index > size || index < 0 || arlist[index]==null){//if out of index or null throw exception
            throw new IndexOutOfBoundsException("Try again, you messed up");
        }
        return (E) arlist[index];//return instance of an Obj.@ specified index
    }

    @Override
    public void remove(int index) {
        int sizeOf = arlist.length;

        if (index > size || index < 0 || arlist[index]==null){//if out of index, throw exception
            throw new IndexOutOfBoundsException("Try again, you messed up");
        }

        Object[] newTempAr = new Object[arlist.length];//temp array list is set to same size as beginning array list
        newTempAr = arlist;//beginning array list is reassigned to new array list

        for(int i = 0; i < index; i++){//for loop copies all data to the left of removal index
            newTempAr[i] = arlist[i];
        }

        for(int i = index; i < size-1; i++){//for loop copies all data to the right of removal index
            newTempAr[i]=arlist[i+1];
        }
        arlist = newTempAr;//array list is reassigned with new length and copied data
        size--;//size decrements
    }

    @Override
    public void clear() {
        size = 0;
        arlist = new Object[1];//reset to empty array with length of 1
    }
}
