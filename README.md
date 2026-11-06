# Composite Pattern — Java Demo

A small Java console demo of the **Composite** design pattern (GoF structural):
files and folders both answer to the same interface, so a whole tree can be
printed or measured without the client knowing which node is a leaf (a file)
and which is a composite (a folder).

## How it works

| Pattern role        | Class                                         | Description                                    |
|---------------------|-----------------------------------------------|------------------------------------------------|
| Component (contract)| `net.saad.composite.FileSystemComponent`      | `print()` and `getTotalSize()` for every node  |
| Leaf                | `net.saad.composite.File`                     | Cannot contain children; its size is its own   |
| Composite           | `net.saad.composite.Folder`                   | Holds children; operations recurse over them   |
| Client              | `net.saad.composite.App`                      | Builds a tree, prints it, removes a subtree    |

The key idea: to the client, `File` and `Folder` look identical — both come
from the same contract. Because every operation is defined recursively over
the children, the tree can be nested arbitrarily deep with no special cases:

```java
Folder root = new Folder("root")
        .add(new Folder("pictures").add(photo1).add(photo2))
        .add(new Folder("documents").add(report))
        .add(budget);
```

## How a call is answered

`getTotalSize()` on a folder walks its subtree; on a file it ends immediately:

```
root.getTotalSize()
= pictures.getTotalSize() + documents.getTotalSize() + budget.getTotalSize()
= (photo1 + photo2) + report + budget
= (123.90 + 12.91) + 23.90 + 13.90
= 174.61
```

`print()` works the same way, indenting one level per nesting depth, and each
folder line already shows the subtotal of its own subtree.

## Project layout

```
src/main/java/net/saad/composite/
├── App.java                     # client: builds, prints, removes
├── FileSystemComponent.java     # component contract
├── File.java                    # leaf
└── Folder.java                  # composite
```

## Requirements

- Java 21+
- Maven 3.9+

## Build & run

```bash
mvn clean compile
java -cp target/classes net.saad.composite.App
```

## Sample output

```
============== File tree ==============
root/  (174.61 KB)
   pictures/  (136.81 KB)
      photo1.jpg  (123.90 KB)
      photo2.jpg  (12.91 KB)
   documents/  (23.90 KB)
      report.docx  (23.90 KB)
   budget.xlsx  (13.90 KB)

Root total size        : 174.61 KB
Single file size       : 123.90 KB
Single folder size     : 136.81 KB

===== After removing documents/ ======
root/  (150.71 KB)
   pictures/  (136.81 KB)
      photo1.jpg  (123.90 KB)
      photo2.jpg  (12.91 KB)
   budget.xlsx  (13.90 KB)

Root total size        : 150.71 KB
```

## Extending

Any new node kind — a shortcut, a compressed archive, a link — is just one
class implementing `FileSystemComponent`: `print(String indent)` and
`getTotalSize()`. The client keeps calling the same two operations on any node
of the tree, and existing classes never change (Open/Closed principle).
