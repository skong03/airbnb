

class Solution20 {
    public static void main(final String[] args) {
        QueueWithArray<Integer> queue=new QueueWithArray<Integer>(5);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);
        queue.add(10);



        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.size());
    }
}

interface SimpleQueue<T> {
    public void add(T t);

    public T peek();

    public T pop();

    public int size();
}

class QueueWithArray<T> implements SimpleQueue<T> {
    Object[] current;
    Object[] tailArray;
    int head=0;
    int tail=0;
    int size=0;

    // size must great than 1
    public QueueWithArray(int arraySize) {
        current = new Object[arraySize];
        tailArray=current;
    }

    @Override
    public void add(T t) {
        // used to have a bug here, be careful
        tailArray[tail]=t;
        size++;
        tail++;
        if(tail==tailArray.length-1){
            Object[] newArray=new Object[tailArray.length];
            tailArray[tail]=newArray;
            tail=0;
            tailArray=newArray;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if(size<=0){
            return null;
        }
        return (T) current[head];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if(size<=0){
            return null;
        }
        T value= (T) current[head];
        head++;
        if(head==current.length-1){
            current= (Object[]) current[head];
            head=0;
        }
        size--;
        return value;
    }

    @Override
    public int size() {
        return this.size;
    }
}