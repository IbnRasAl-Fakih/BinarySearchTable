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
        System.out.println(tree.size());

        tree.delete(90);
        System.out.println(tree.size());

        for (var elem : tree) {
            System.out.println("Key is " + elem.key + " and value is " + elem.value);
        }

        System.out.println(tree.get(75));

    }
}