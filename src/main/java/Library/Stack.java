package Library;

/**
 *
 * @author ulil
 */
public class Stack<T> {
    
    private T[] data;
    private int top = -1;
    private final int MAX = 1000;
 
    public Stack(){
        data = (T[]) new Object[MAX];
        top  = 0;
    }
 
    public void push(T info){
        top++;
        data[top] = info;
    }
 
    public T pop(){
        T hasil = data[top];
        top--;
        return hasil;
    }
 
    public int size(){
        return top;
    }
 
    public T infoTop(int top){
        return data[top];
    }
 
    public boolean isEmpty(){
        return top==0;
    }
    
    public T delete(int top){
        T hasil = data[top];
        for(int i=top+1; i<=this.top; i++){
            data[i-1] = data[i];
        }
        this.pop();
        return hasil;
    }
    
    public void replace(int top, T infoTop){
        data[top] = infoTop;
    }
 
    public double clear(){
        double result = 0;
        for(int i=1; i<=top; i++){
            pop();
            result = (Double) data[i];
        }
        return result;
    }
}
