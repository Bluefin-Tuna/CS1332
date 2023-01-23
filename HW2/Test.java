public class Test {
    public static void main(String[] args) {
        CircularSinglyLinkedList<String> list = new CircularSinglyLinkedList<>();
        list.addToBack("0");                  //0
        System.out.println("Removed: " + list.removeLastOccurrence("0"));
        System.out.println("----------------------------------");
        list.addToBack("0");                  //0
        list.addToBack("1");                  //0,1
        System.out.println("Removed: " + list.removeLastOccurrence("1"));
        System.out.println("----------------------------------");
        // list.clear();
        // list.addToBack("0");
        list.print();
        list.addToBack("1");                  //0,1
        list.print();
        list.addToBack("5");                  //0,1,2
        list.print();
        System.out.println("Removed: " + list.removeLastOccurrence("1"));
        System.out.println("----------------------------------");
        list.print();
        // list.clear();                         //
        // list.addToBack("1");                  //1
        // list.addToBack("1");                  //1,1
        // list.addToBack("1");                  //1,1,1
        // list.addToBack("1");                  //1,1,1,1
        // list.addToBack("0");                  //1,1,1,1,0
        // list.addToBack("1");                  //1,1,1,1,0,1
        // list.addToBack("0");                  //1,1,1,1,0,1,0
        // // System.out.println("Removed: " + list.removeLastOccurrence("1"));
        // System.out.println("----------------------------------");
        // list.print();
    }
}
