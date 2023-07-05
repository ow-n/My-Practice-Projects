package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class ApplicationDriver {

   public static void main(String[] args) {
      int[] arraySizes = { 50000, 100000, 150000, 200000, 250000, 300000 };
      int[][] arraysBySize = new int[arraySizes.length][];
      for (int i = 0; i < arraySizes.length; i++) {
         arraysBySize[i] = generateRandomArray(arraySizes[i]);
      }

      System.out.println(
            "| Array Size |   50,000 |  100,000 |  150,000 |  200,000 |" + 
            "  250,000 |  300,000 |");
      System.out.println(
            "|------------|----------|----------|----------|----------|" + 
            "----------|----------|");

      performAndPrint("InsertionS", arraysBySize, new InsertionSort());
      performAndPrint("Bubble Sor", arraysBySize, new BubbleSort());
      performAndPrint("Merge Sort", arraysBySize, new MergeSort());
      performAndPrint("Quick Sort", arraysBySize, new QuickSort());
      performAndPrint("Heap Sort", arraysBySize, new HeapSortAlgorithm());
      performAndPrint("Radix Sort", arraysBySize, new RadixSortAlgorithm());
   }

   private static int[] generateRandomArray(int size) {
      int[] arr = new int[size];
      for (int i = 0; i < size; i++) {
         arr[i] = (int) (Math.random() * 1000);
      }
      return arr;
   }

   private static void performAndPrint(String name, int[][] arraysBySize,
         SortAlgorithm sortAlgorithm) {
      System.out.printf("| %-10s", name);
      for (int[] arr : arraysBySize) {
         int[] arrCopy = Arrays.copyOf(arr, arr.length);
         long startTime = System.nanoTime();
         sortAlgorithm.sort(arrCopy);
         long endTime = System.nanoTime();
         long executionTime = (endTime - startTime) / 1000000; // Convert to ms

         System.out.printf(" | %6dms", executionTime);
      }
      System.out.println(" |");
   }

}

/******************************************************************************/
/*                                  Output 1                                  */
/****************************************************************************//*
| Array Size |   50,000 |  100,000 |  150,000 |  200,000 |  250,000 |  300,000 |
|------------|----------|----------|----------|----------|----------|----------|
| InsertionS |    844ms |   2534ms |   1951ms |   3528ms |   5398ms |   7918ms |
| Bubble Sor |   3342ms |  13155ms |  28069ms |  52293ms |  78509ms | 104967ms |
| Merge Sort |      7ms |     17ms |     16ms |     26ms |     27ms |     40ms |
| Quick Sort |     19ms |     18ms |     25ms |     39ms |     58ms |     80ms |
| Heap Sort  |     43ms |     34ms |     55ms |     64ms |    103ms |    123ms |
| Radix Sort |      5ms |      6ms |      9ms |      7ms |      9ms |     11ms |
/******************************************************************************/


/******************************************************************************/
/*                                  Output 2                                  */
/****************************************************************************//*
| Array Size |   50,000 |  100,000 |  150,000 |  200,000 |  250,000 |  300,000 |
|------------|----------|----------|----------|----------|----------|----------|
| InsertionS |    864ms |   2741ms |   1782ms |   3168ms |   4989ms |   7180ms |
| Bubble Sor |   2874ms |  12208ms |  28243ms |  52298ms |  81333ms | 108704ms |
| Merge Sort |      8ms |     17ms |     19ms |     30ms |     33ms |     47ms |
| Quick Sort |     11ms |     12ms |     28ms |     52ms |     70ms |    100ms |
| Heap Sort  |     56ms |     54ms |     71ms |     76ms |    153ms |    159ms |
| Radix Sort |      5ms |      8ms |     11ms |      9ms |     11ms |     13ms |
/******************************************************************************/


/******************************************************************************/
/*                                  Output 3                                  */
/****************************************************************************//*
| Array Size |   50,000 |  100,000 |  150,000 |  200,000 |  250,000 |  300,000 |
|------------|----------|----------|----------|----------|----------|----------|
| InsertionS |    864ms |   2741ms |   1782ms |   3168ms |   4989ms |   7180ms |
| Bubble Sor |   2874ms |  12208ms |  28243ms |  52298ms |  81333ms | 108704ms |
| Merge Sort |      8ms |     17ms |     19ms |     30ms |     33ms |     47ms |
| Quick Sort |     11ms |     12ms |     28ms |     52ms |     70ms |    100ms |
| Heap Sort  |     56ms |     54ms |     71ms |     76ms |    153ms |    159ms |
| Radix Sort |      5ms |      8ms |     11ms |      9ms |     11ms |     13ms |
/******************************************************************************/


/* ========================================================================== */

interface SortAlgorithm {
   void sort(int[] array);
}

/* ========================================================================== */

class InsertionSort implements SortAlgorithm {
   /** The method for sorting the numbers */
   public void sort(int[] list) {
      for (int i = 1; i < list.length; i++) {
         /**
          * insert list[i] into a sorted sublist list[0..i-1] so that list[0..i]
          * is sorted.
          */
         int currentElement = list[i];
         int k;
         for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
            list[k + 1] = list[k];
         }

         // Insert the current element into list[k+1]
         list[k + 1] = currentElement;
      }
   }
}

/* ========================================================================== */

class BubbleSort implements SortAlgorithm {
   /** Bubble sort method */
   public void sort(int[] list) {
      boolean needNextPass = true;

      for (int k = 1; k < list.length && needNextPass; k++) {
         // Array may be sorted and next pass not needed
         needNextPass = false;
         for (int i = 0; i < list.length - k; i++) {
            if (list[i] > list[i + 1]) {
               // Swap list[i] with list[i + 1]
               int temp = list[i];
               list[i] = list[i + 1];
               list[i + 1] = temp;

               needNextPass = true; // Next pass still needed
            }
         }
      }
   }
}

/* ========================================================================== */

class MergeSort implements SortAlgorithm {
   /** The method for sorting the numbers */
   public void sort(int[] list) {
      if (list.length > 1) {
         // Merge sort the first half
         int[] firstHalf = new int[list.length / 2];
         System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
         sort(firstHalf);

         // Merge sort the second half
         int secondHalfLength = list.length - list.length / 2;
         int[] secondHalf = new int[secondHalfLength];
         System.arraycopy(list, list.length / 2, secondHalf, 0,
               secondHalfLength);
         sort(secondHalf);

         // Merge firstHalf with secondHalf into list
         merge(firstHalf, secondHalf, list);
      }
   }

   /** Merge two sorted lists */
   public static void merge(int[] list1, int[] list2, int[] temp) {
      int current1 = 0; // Current index in list1
      int current2 = 0; // Current index in list2
      int current3 = 0; // Current index in temp

      while (current1 < list1.length && current2 < list2.length) {
         if (list1[current1] < list2[current2])
            temp[current3++] = list1[current1++];
         else
            temp[current3++] = list2[current2++];
      }

      while (current1 < list1.length)
         temp[current3++] = list1[current1++];

      while (current2 < list2.length)
         temp[current3++] = list2[current2++];
   }
}

/* ========================================================================== */

class QuickSort implements SortAlgorithm {
   public void sort(int[] list) {
      quickSort(list, 0, list.length - 1);
   }

   private static void quickSort(int[] list, int first, int last) {
      if (last > first) {
         int pivotIndex = partition(list, first, last);
         quickSort(list, first, pivotIndex - 1);
         quickSort(list, pivotIndex + 1, last);
      }
   }

   /** Partition the array list[first..last] */
   private static int partition(int[] list, int first, int last) {
      int pivot = list[first]; // Choose the first element as the pivot
      int low = first + 1; // Index for forward search
      int high = last; // Index for backward search

      while (high > low) {
         // Search forward from left
         while (low <= high && list[low] <= pivot)
            low++;

         // Search backward from right
         while (low <= high && list[high] > pivot)
            high--;

         // Swap two elements in the list
         if (high > low) {
            int temp = list[high];
            list[high] = list[low];
            list[low] = temp;
         }
      }

      while (high > first && list[high] >= pivot)
         high--;

      // Swap pivot with list[high]
      if (pivot > list[high]) {
         list[first] = list[high];
         list[high] = pivot;
         return high;
      } else {
         return first;
      }
   }
}

/* ========================================================================== */

class Heap<E> {
   private java.util.ArrayList<E> list = new java.util.ArrayList<>();
   private java.util.Comparator<? super E> c;

   /** Create a default heap using a natural order for comparison */
   public Heap() {
      this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
   }

   /** Create a heap with a specified comparator */
   public Heap(java.util.Comparator<E> c) {
      this.c = c;
   }

   /** Create a heap from an array of objects */
   public Heap(E[] objects) {
      this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
      for (int i = 0; i < objects.length; i++)
         add(objects[i]);
   }

   /** Add a new object into the heap */
   public void add(E newObject) {
      list.add(newObject); // Append to the heap
      int currentIndex = list.size() - 1; // The index of the last node

      while (currentIndex > 0) {
         int parentIndex = (currentIndex - 1) / 2;
         // Swap if the current object is greater than its parent
         if (c.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
            E temp = list.get(currentIndex);
            list.set(currentIndex, list.get(parentIndex));
            list.set(parentIndex, temp);
         } else
            break; // the tree is a heap now

         currentIndex = parentIndex;
      }
   }

   /** Remove the root from the heap */
   public E remove() {
      if (list.size() == 0)
         return null;

      E removedObject = list.get(0);
      list.set(0, list.get(list.size() - 1));
      list.remove(list.size() - 1);

      int currentIndex = 0;
      while (currentIndex < list.size()) {
         int leftChildIndex = 2 * currentIndex + 1;
         int rightChildIndex = 2 * currentIndex + 2;

         // Find the maximum between two children
         if (leftChildIndex >= list.size())
            break; // The tree is a heap
         int maxIndex = leftChildIndex;
         if (rightChildIndex < list.size()) {
            if (c.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
               maxIndex = rightChildIndex;
            }
         }

         // Swap if the current node is less than the maximum
         if (c.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
            E temp = list.get(maxIndex);
            list.set(maxIndex, list.get(currentIndex));
            list.set(currentIndex, temp);
            currentIndex = maxIndex;
         } else
            break; // The tree is a heap
      }

      return removedObject;
   }

   /** Get the number of nodes in the tree */
   public int getSize() {
      return list.size();
   }

   /** Return true if heap is empty */
   public boolean isEmpty() {
      return list.size() == 0;
   }
}

/* ========================================================================== */

class HeapSort {
   /** Heap sort method */
   public static <E> void heapSort(E[] list) {
      // Create a Heap of integers
      heapSort(list, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
   }

   /** Heap sort method */
   public static <E> void heapSort(E[] list, Comparator<E> c) {
      // Create a Heap of integers
      Heap<E> heap = new Heap<>(c);

      // Add elements to the heap
      for (int i = 0; i < list.length; i++)
         heap.add(list[i]);

      // Remove elements from the heap
      for (int i = list.length - 1; i >= 0; i--)
         list[i] = heap.remove();
   }
}

/* ========================================================================== */

class HeapSortAlgorithm implements SortAlgorithm {
   public void sort(int[] array) {
      Integer[] newArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
      HeapSort.heapSort(newArray);
      for (int i = 0; i < array.length; i++) {
         array[i] = newArray[i];
      }
   }
}

/* ========================================================================== */

class RadixSort {

   // Using counting sort to sort the elements in the basis of significant place
   void countingSort(int array[], int size, int place) {
      int[] output = new int[size + 1];
      int max = array[0];
      for (int i = 1; i < size; i++) {
         if (array[i] > max)
            max = array[i];
      }
      int[] count = new int[max + 1];

      for (int i = 0; i < max; ++i)
         count[i] = 0;

      // Calculate count of elements
      for (int i = 0; i < size; i++)
         count[(array[i] / place) % 10]++;

      // Calculate cumulative count
      for (int i = 1; i < 10; i++)
         count[i] += count[i - 1];

      // Place the elements in sorted order
      for (int i = size - 1; i >= 0; i--) {
         output[count[(array[i] / place) % 10] - 1] = array[i];
         count[(array[i] / place) % 10]--;
      }

      for (int i = 0; i < size; i++)
         array[i] = output[i];
   }

   // Function to get the largest element from an array
   int getMax(int array[], int n) {
      int max = array[0];
      for (int i = 1; i < n; i++)
         if (array[i] > max)
            max = array[i];
      return max;
   }

   // Main function to implement radix sort
   void sort(int array[], int size) {
      // Get maximum element
      int max = getMax(array, size);

      // Apply counting sort to sort elements based on place value.
      for (int place = 1; max / place > 0; place *= 10)
         countingSort(array, size, place);
   }
}

/* ========================================================================== */

class RadixSortAlgorithm implements SortAlgorithm {
   public void sort(int[] array) {
      new RadixSort().sort(array, array.length);
   }
}

/* ========================================================================== */