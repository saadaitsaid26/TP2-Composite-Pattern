package net.saad.composite;

/**
 * The component contract of the Composite pattern. Files (leaves) and folders
 * (composites) expose the same operations, so any node of the tree can be
 * used without knowing its kind.
 */
public interface FileSystemComponent {

    /** Prints the tree rooted at this component, from the top level. */
    default void print() {
        print("");
    }

    /**
     * Prints the tree rooted at this component, indenting every nested line
     * with {@code indent}.
     */
    void print(String indent);

    /** Total size (in KB) of the subtree rooted at this component. */
    double getTotalSize();
}
