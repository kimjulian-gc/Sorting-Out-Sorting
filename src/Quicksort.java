import java.util.Comparator;

/**
 * Sort using Quicksort.
 *
 * @author Your Name Here
 */

public class Quicksort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new Quicksort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  Quicksort() {} // Quicksort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    sort(values, order, 0, values.length);
  }

  private static <T> void sort(T[] values, Comparator<? super T> order, int lb, int ub) {

    if (ub - lb <= 1) {
      return;
    }

    int pivotIndex = partition(values, order, lb, ub);
    sort(values, order, lb, pivotIndex);
    sort(values, order, pivotIndex, ub);
  }

  public static <T> int partition(T[] arr, Comparator<? super T> compare, int lb, int ub) {
    int pivotIndex = (lb + ub) / 2;
    SortUtils.swap(arr, lb, pivotIndex);
    T pivot = arr[lb];

    int small = lb + 1;
    int large = ub;

    while (small < large) {
      T lowerValue = arr[small];
      T upperValue = arr[large - 1];

      if (compare.compare(lowerValue, pivot) <= 0) {
        small++;
      } else if (compare.compare(upperValue, pivot) > 0) {
        large--;
      } else {
        SortUtils.swap(arr, small, large - 1);
      }
    }

    SortUtils.swap(arr, lb, small - 1);

    return small - 1;
  } // partition
} // class Quicksort
