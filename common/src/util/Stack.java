package util;


public interface Stack <T>{
    T pop();
    void push(T v);

    void reset();
    boolean isEmpty();
}
