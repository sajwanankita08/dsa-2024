package heap;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Comparator;

public class HeapWithComparator<T> {
    private T[] heap;
    private int maxSize;
    private int currIndex;

    Comparator<T> comparator;

    public HeapWithComparator(Class<T> clazz, int size,Comparator comparator ){
        this.maxSize=size;
        this.currIndex =-1;
        this.heap = (T[]) Array.newInstance(clazz, size);
        this.comparator= comparator;
    }

    private int parent(int index){
        return (index-1)/2;
    }
    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index + 2;
    }

    private boolean isLeaf(int index){
        return index > (currIndex /2 -1);
    }

    private void swap(int index1, int index2){
        T temp= heap[index1];
        heap[index1]=heap[index2];
        heap[index2]=temp;
    }

    public void insert(T element){
        currIndex++;
        if(currIndex > maxSize-1){
            System.out.println("heap reached max size");
            return;
        };

        heap[currIndex]=element;
        int index=currIndex;
        upHeapify(index);
    }

    public T peek(){
        if(currIndex<0){
            System.out.println("heap is empty");
            return null;
        }
        return heap[0];
    }
    //MAX_HEAP
    private void upHeapifyRecursion(int index){
        if(index<=0){
            return;
        }
        if(Objects.compare(heap[parent(index)],heap[index], this.comparator)==-1){
            swap(parent(index),index);
            upHeapifyRecursion(parent(index));
        }
    }

    private void upHeapify(int index){
        while(Objects.compare(heap[parent(index)],heap[index], this.comparator)==-1){
            swap(parent(index),index);
            index=parent(index);
        }
    }


}
