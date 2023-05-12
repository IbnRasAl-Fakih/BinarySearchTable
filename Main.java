public class Main {
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();
        tree.put(75, "Almaty");
        tree.put(69, "Astana");
        tree.put(90, "London");
        tree.put(105, "New-York");
        tree.put(120, "Berlin");
        tree.put(145, "Moskva");
        tree.put(13, "Shymkent");
        tree.put(45, "Semei");
        tree.put(71, "Taraz");

        tree.delete(105);

        for (var elem : tree) {
            System.out.println("Key is " + elem.key + " and value is " + elem.value);
        }

    }
}