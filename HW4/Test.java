public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>();
        tree.add(50);
        tree.add(25);
        tree.add(75);
        tree.add(12);
        tree.add(37);
        tree.add(10);
        tree.add(15);
        tree.add(40);
        tree.add(13);
        System.out.print(tree.kLargest(10));
    }
}
