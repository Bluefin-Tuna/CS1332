public class Test {
    public static void main(String[] args) {
        CircularSinglyLinkedList<String> list = new CircularSinglyLinkedList<>();
        System.out.println("started");
        list.addToBack("0");
        list.addToBack("1");
        list.removeLastOccurrence("1");
        list.addToBack("1");
        list.addToBack("2");
        // list.removeLastOccurrence("1");
        // list.addToBack("2a");
        // list.addToBack("3a");
        // list.addToBack("0a");
        // System.out.println(list.get(5) + list.removeLastOccurrence("0a"));

        int j = 1 > 0 ? 1: 0;
    }
}
