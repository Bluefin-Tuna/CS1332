public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToFront("" + i);
        }
        System.out.println(list.removeFromBack());
    }
}