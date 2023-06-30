package binarysearchtree;

class FHTreeNode<E> {
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected FHTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected boolean deleted;
   protected FHTreeNode<E> myRoot; // needed to test for certain error

   public FHTreeNode(E d, FHTreeNode<E> sb, FHTreeNode<E> chld,
         FHTreeNode<E> prv, boolean del) {
      firstChild = chld;
      sib = sb;
      prev = prv;
      data = d;
      deleted = del;
      myRoot = null;
   }

   public FHTreeNode() {
      this(null, null, null, null, false);
   }

   public E getData() {
      return data;
   }

   // for use only by FHtree (default access)
   protected FHTreeNode(E d, FHTreeNode<E> sb, FHTreeNode<E> chld,
         FHTreeNode<E> prv, FHTreeNode<E> root, boolean del) {
      this(d, sb, chld, prv, del);
      myRoot = root;
   }
}