package net.saad.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A composite: holds other components (files and/or folders). Its operations
 * are defined recursively over its children, which keeps the tree consistent
 * no matter how deep it gets.
 */
public class Folder implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    /** Adds a child and returns this folder, so adds can be chained. */
    public Folder add(FileSystemComponent component) {
        children.add(component);
        return this;
    }

    public Folder remove(FileSystemComponent component) {
        children.remove(component);
        return this;
    }

    @Override
    public void print(String indent) {
        System.out.printf(Locale.ROOT, "%s%s/  (%.2f KB)%n", indent, name, getTotalSize());
        if (children.isEmpty()) {
            System.out.println(indent + "  <empty>");
            return;
        }
        for (FileSystemComponent child : children) {
            child.print(indent + "   ");
        }
    }

    @Override
    public double getTotalSize() {
        double total = 0;
        for (FileSystemComponent child : children) {
            total += child.getTotalSize();
        }
        return total;
    }
}
