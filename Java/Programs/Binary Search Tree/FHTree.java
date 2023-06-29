package binarysearchtree;

class FHTree<E> implements Cloneable {
   private int mSize;
   FHTreeNode<E> mRoot;

   public FHTree() {
      clear();
   }

   public boolean empty() {
      return (mSize == 0);
   }

   public int size() {
      return calculateSize(mRoot);
   }

   private int calculateSize(FHTreeNode<E> node) {
      if (node == null || node.deleted)
         return 0;

      int size = 1;
      FHTreeNode<E> child = node.firstChild;
      while (child != null) {
         size += calculateSize(child);
         child = child.sib;
      }

      return size;
   }

   public void clear() {
      mSize = 0;
      mRoot = null;
   }

   public FHTreeNode<E> find(E x) {
      return find(mRoot, x, 0);
   }

   public boolean remove(E x) {
      return remove(mRoot, x);
   }

   public void display() {
      display(mRoot, 0);
   }

   public <F extends Traverser<? super E>> void traverse(F func) {
      traverse(func, mRoot, 0);
   }

   public FHTreeNode<E> addChild(FHTreeNode<E> treeNode, E x) {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0) {
         if (treeNode != null && treeNode.deleted)
            return null; // error something's fishy. treeNode can't right
         mRoot = new FHTreeNode<E>(x, null, null, null, false);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null; // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHTreeNode<E> newNode = new FHTreeNode<E>(x, treeNode.firstChild, null,
            treeNode, mRoot, false); // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;
   }

   public FHTreeNode<E> find(FHTreeNode<E> root, E x, int level) {
      FHTreeNode<E> retval;

      if (mSize == 0 || root == null)
         return null;

      if (root.data.equals(x))
         return root.deleted ? null : root;

      // otherwise, recurse. don't process sibs if this was the original call
      if (level > 0 && (retval = find(root.sib, x, level)) != null)
         return retval;
      return find(root.firstChild, x, ++level);
   }

   public boolean remove(FHTreeNode<E> root, E x) {
      FHTreeNode<E> tn = null;

      if (mSize == 0 || root == null)
         return false;

      if ((tn = find(root, x, 0)) != null) {
         tn.deleted = true; // marks node as deleted instead of removing
         mSize--;
         return true;
      }
      return false;
   }

   private void removeNode(FHTreeNode<E> nodeToDelete) {
      if (nodeToDelete == null || mRoot == null)
         return;
      if (nodeToDelete.myRoot != mRoot)
         return; // silent error, node does not belong to this tree

      // remove all the children of this node
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);

      if (nodeToDelete.prev == null)
         mRoot = null; // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib; // adjust parent

      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev;
   }

   public Object clone() throws CloneNotSupportedException {
      FHTree<E> newObject = (FHTree<E>) super.clone();
      newObject.clear(); // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);

      return newObject;
   }

   private FHTreeNode<E> cloneSubtree(FHTreeNode<E> root) {
      FHTreeNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHTreeNode<E>(root.data, cloneSubtree(root.sib),
            cloneSubtree(root.firstChild), null, false);

      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }

   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHTreeNode<E> treeNode) {
      if (treeNode == null)
         return;

      treeNode.myRoot = mRoot;
      setMyRoots(treeNode.sib);
      setMyRoots(treeNode.firstChild);
   }

   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";

   // let be public so client can call on subtree
   public void display(FHTreeNode<E> treeNode, int level) {
      String indent;

      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1) {
         System.out.println(blankString + " ... ");
         return;
      }

      if (treeNode == null || treeNode.deleted)
         return;

      indent = blankString.substring(0, level);

      System.out.println(indent + treeNode.data);

      // recursive step done here
      display(treeNode.firstChild, level + 1);
      if (level > 0)
         display(treeNode.sib, level);
   }

   // often helper of typical public version, but also callable by on subtree
   public <F extends Traverser<? super E>> void traverse(F func,
         FHTreeNode<E> treeNode, int level) {
      if (treeNode == null || treeNode.deleted)
         return;

      func.visit(treeNode.data);

      // recursive step done here
      traverse(func, treeNode.firstChild, level + 1);
      if (level > 0)
         traverse(func, treeNode.sib, level);
   }

   public int sizePhysical() {
      return sizePhysical(mRoot);
   }

   private int sizePhysical(FHTreeNode<E> node) {
      if (node == null)
         return 0;

      int size = 1;
      FHTreeNode<E> child = node.firstChild;
      while (child != null) {
         size += sizePhysical(child);
         child = child.sib;
      }

      return size;
   }

   // like the old display(). Ignores the deleted flag.
   public void displayPhysical() {
      displayPhysical(mRoot, 0);
   }

   public void displayPhysical(FHTreeNode<E> treeNode, int level) {
      String indent;

      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1) {
         System.out.println(blankString + " ... ");
         return;
      }

      if (treeNode == null)
         return;

      indent = blankString.substring(0, level);

      // Optionally, place a " (D)" after any node that has deleted == true
      System.out
            .println(indent + treeNode.data + (treeNode.deleted ? " (D)" : ""));

      // recursive step done here
      displayPhysical(treeNode.firstChild, level + 1);
      if (level > 0)
         displayPhysical(treeNode.sib, level);
   }

   // physically removes all nodes that are marked as deleted
   public void collectGarbage() {
      collectGarbage(mRoot);
   }

   private void collectGarbage(FHTreeNode<E> node) {
      if (node == null)
         return;

      if (node.deleted) {
         removeNode(node);
      } else {
         collectGarbage(node.firstChild);
         collectGarbage(node.sib);
      }
   }
}