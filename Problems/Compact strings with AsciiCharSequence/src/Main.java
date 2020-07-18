import java.util.Arrays;

class AsciiCharSequence implements CharSequence{
    // implementation
    byte[] arr;

    public AsciiCharSequence(byte[] arr) {
        this.arr = arr;

    }

    @Override
    public int length() {
        return arr.length;
    }

    @Override
    public char charAt(int i) {
        return (char)arr[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        CharSequence cs = new AsciiCharSequence(Arrays.copyOfRange(arr, i, i1));
        return cs ;
    }

    @Override
    public String toString() {
        return new String(arr);
    }
}