package util;

public class StackImpl <E> implements Stack<E> {
    public static final int DEFAULT_CAPACITY = 16;

    private Object[] value;
    private int tos;

    public StackImpl() {
        this(DEFAULT_CAPACITY);
    }

    public StackImpl(int capacity) {
        this.value = new Object[capacity];
        tos = -1;
    }

    public E pop() {
        if (tos == -1) {
            return null;
        }
        return (E)value[tos--];
    }

    public void push(E v) {
        if (tos == value.length - 1) {
            ensureCapacity();
        }
        value[++tos] = v;
    }

    private void ensureCapacity() {
        Object[] temp = new Object[(int) (1.5 * value.length) + 1];
        System.arraycopy(value, 0, temp, 0, value.length);
        value = temp;
    }

    public void reset() {
        tos = -1;

    }

    public boolean isEmpty() {
        return tos == -1;
    }

    public static void main(String[] args) {
        BraceChecker.BracketItem bracketItem = new BraceChecker.BracketItem('{', 0,0,0);
    }
}