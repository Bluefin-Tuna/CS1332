public class Test {
    public static void main(String[] args) {
        ExternalChainingHashMap<Integer, String> map = new ExternalChainingHashMap<>();
        map.put(0, "A");
        map.put(4, "E"); 
        map.put(14, "D_1");
        map.put(36, "D_3");
        map.put(6, "G");
        map.put(17, "G_1");
        map.put(11, "A_1");

        System.out.println(map.remove(36));
        System.out.println(map.remove(0));
        System.out.println(map.remove(17));
        System.out.println(map.size());
    }
}
