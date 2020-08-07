public class GenericDemo {
    // 类型参数需要紧接着放在返回值类型前面，返回值类型必须紧接着放在方法名前面
    public static <T> void reverse(T[] data) {
        int low = 0, high = data.length - 1;
        while (low < high) {
            T temp = data[low];
            data[low++] = data[high];
            data[high--] = temp;
        }
    }
}
