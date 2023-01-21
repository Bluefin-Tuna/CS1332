public class Test {
    public static void main(String[] args) {
        CircularSinglyLinkedList<String> list = new CircularSinglyLinkedList<>();
        String temp = "0a";
        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        list.print();
        System.out.println(list.size());
        System.out.println(list.removeFromFront());
        list.print();
        System.out.println(list.size());
        System.out.print(list.getHead());
    }
}
