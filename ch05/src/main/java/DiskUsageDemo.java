import java.io.File;

public class DiskUsageDemo {

    public static long diskUsage(File root) {
        long total = root.length();
        if (root.isDirectory()) {
            for (String childname : root.list()) {
                File child = new File(root, childname);
                total += diskUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }

    public static void main(String[] args) {
        File Desktop = new File("C:\\Users\\tao\\Desktop");
        System.out.println(diskUsage(Desktop));
    }
}
