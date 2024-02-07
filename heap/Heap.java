package heap;

public class Heap {
    private int[] heap;
    private int maxSize;
    private int currIndex;

    public Heap(int size){
        this.maxSize=size;
        this.currIndex =-1;
        this.heap= new int[maxSize];
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
        int temp= heap[index1];
        heap[index1]=heap[index2];
        heap[index2]=temp;
    }

    public void insert(int element){
        currIndex++;
        if(currIndex > maxSize-1){
            System.out.println("heap reached max size");
            return;
        };

        heap[currIndex]=element;
        int index=currIndex;
        upHeapify(index);
    }
    //MAX_HEAP
    private void upHeapifyRecursion(int index){
        if(index<=0){
            return;
        }
        if(heap[parent(index)]<heap[index]){
            swap(parent(index),index);
            upHeapifyRecursion(parent(index));
        }
    }

    private void upHeapify(int index){
        while(index>0 && heap[parent(index)] < heap[index]){
            swap(parent(index),index);
            index=parent(index);
        }
    }

    private void downHeapifyRecursion(int index){
        if(index>currIndex){
            return;
        }
        int largest=index;
        if(leftChild(index) <= currIndex && heap[leftChild(index)] > heap[index]){
            largest=leftChild(index);
        }
        if(rightChild(index) <= currIndex && heap[rightChild(index)] > heap[largest]){
            largest=rightChild(index);
        }
        if(largest!=index){
            swap(largest,index);
            index=largest;
            downHeapify(largest);
        }

    }

    private void downHeapify(int index){
        while(index<=currIndex){
            int largest=index;
            if(leftChild(index) <= currIndex && heap[leftChild(index)] > heap[index]){
                largest=leftChild(index);
            }
            if(rightChild(index) <= currIndex && heap[rightChild(index)] > heap[largest]){
                largest=rightChild(index);
            }
            if(largest==index){
                break;
            }
            swap(largest,index);
            index=largest;
        }
    }

    public void remove(){
            if(currIndex==-1){
                System.out.println("heap is empty");
                return;
            }
            int num=heap[0];
            heap[0]=heap[currIndex];
            currIndex--;
            downHeapify(0);
    }

}
