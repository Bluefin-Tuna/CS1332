public class Test {
    public static void main(String[] args) {
        ExternalChainingHashMap<Integer, String> map = new ExternalChainingHashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(11, "K");
        map.put(6, "F");
        map.put(7, "G");
        map.put(8, "H");

        map.print();
        System.out.print(map.values());
    }
}
