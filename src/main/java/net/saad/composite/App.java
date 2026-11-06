package net.saad.composite;

import java.util.Locale;

/**
 * Client entry point for the Composite pattern demo: builds a file/folder
 * tree, prints it, and shows that every node answers identically to
 * {@code print()} and {@code getTotalSize()} — whether it is a leaf or a
 * composite. Removing a subtree keeps the whole answer consistent.
 */
public class App {

    public static void main(String[] args) {

        File photo1 = new File("photo1.jpg", 123.9);
        File photo2 = new File("photo2.jpg", 12.907);
        File report = new File("report.docx", 23.9);
        File budget = new File("budget.xlsx", 13.9);

        Folder pictures = new Folder("pictures")
                .add(photo1)
                .add(photo2);

        Folder documents = new Folder("documents")
                .add(report);

        Folder root = new Folder("root")
                .add(pictures)
                .add(documents)
                .add(budget);

        System.out.println("============== File tree ==============");
        root.print();
        System.out.println();
        System.out.println("Root total size        : " + sizeOf(root));
        System.out.println("Single file size       : " + sizeOf(photo1));
        System.out.println("Single folder size     : " + sizeOf(pictures));

        root.remove(documents);

        System.out.println();
        System.out.println("===== After removing documents/ ======");
        root.print();
        System.out.println();
        System.out.println("Root total size        : " + sizeOf(root));
    }

    private static String sizeOf(FileSystemComponent component) {
        return String.format(Locale.ROOT, "%.2f KB", component.getTotalSize());
    }
}
