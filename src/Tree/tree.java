package Tree;

// tree.java
// demonstrates binary tree

////////////////////////////////////////////////////
class Node {
    public int iData;
    public double dData;
    public Node leftChild;
    public Node rightChild;

    public void displayNode() {
        System.out.print("{");
        System.out.print(iData);
        System.out.print(", ");
        System.out.print(dData);
        System.out.print("} ");
    }
} // end class Node
////////////////////////////////////////////////////
class Tree {
    private Node root;  // first node of tree
    //------------------------------------
    public Tree() {
        // constructor. no nodes in tree yet
        root = null;
    }
    //--------find node with given key (assumes non-empty tree)--------
    public Node find(int key) {
        Node current = root; // start at root
        while (current.iData != key) {
            // while no match
            if (key < current.iData)
                // go left
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null) // if no child, didn't find it
                return null;
        }
        return current; // found it
    }
    //------------------------------------
    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;
        if (root == null) // no node in root
            root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.iData) {
                    // go left
                    current = current.leftChild;
                    if (current == null) {
                        // if end of the line, insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    // go right
                    current = current.rightChild;
                    if (current == null) {
                        // if end of the line, insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                } // end else go right
            } // end while
        }
    } // end insert()
    //--------------delete node with given key (assume non-empty list)------------------
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                // go left
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                // end of the line, didn't find it
                return false;
        } //end while

        // found node to delete

        if (current.leftChild == null && current.rightChild == null) {
            // if no children, simply delete it
            if (current == root)
                // if root, tree is empty
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        } else if (current.rightChild == null) {
            // if no right child, replace with left subtree
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        } else if (current.leftChild == null) {
            // if no left child, replace with right subtree
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        } else {
            // two children, so replace with inorder successor
            // get successor of node to delete
            Node successor = getSuccessor(current);

            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        } // end else two children
        // succssor cannot have a left child
        return true;
    }// end delete()
    //------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendants
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            // go to right child until no more left children
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
}
