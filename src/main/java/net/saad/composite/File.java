package net.saad.composite;

import java.util.Locale;

/**
 * A leaf: cannot contain other components; its total size is simply its own.
 */
public class File implements FileSystemComponent {

    private final String name;
    private final double size;

    public File(String name, double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void print(String indent) {
        System.out.printf(Locale.ROOT, "%s%s  (%.2f KB)%n", indent, name, size);
    }

    @Override
    public double getTotalSize() {
        return size;
    }
}
