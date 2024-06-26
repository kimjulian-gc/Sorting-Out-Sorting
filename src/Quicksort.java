import java.util.Comparator;

/**
 * Sort using Quicksort.
 *
 * @author Julian Kim
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

  private static <T> int partition(T[] arr, Comparator<? super T> compare, int lb, int ub) {
    medianOfThreePivot(arr, lb, ub, compare);
    T pivot = arr[lb];

    int small = lb + 1;
    int large = ub;

    while (small < large) {
      if (compare.compare(arr[small], pivot) <= 0) {
        small++;
        continue;
      }

      if (compare.compare(arr[large - 1], pivot) > 0) {
        large--;
        continue;
      }

      SortUtils.swap(arr, small, large - 1);
    }

    SortUtils.swap(arr, lb, small - 1);

    return small - 1;
  } // partition

  private static <T> void medianOfThreePivot(T[] arr, int lb, int ub,
      Comparator<? super T> compare) {
    int mid = (lb + ub) / 2;

    if (compare.compare(arr[lb], arr[mid]) > 0)
      SortUtils.swap(arr, lb, mid);

    if (compare.compare(arr[lb], arr[ub - 1]) > 0)
      SortUtils.swap(arr, lb, ub - 1);

    if (compare.compare(arr[mid], arr[ub - 1]) > 0)
      SortUtils.swap(arr, mid, ub - 1);

    SortUtils.swap(arr, lb, mid);
  }
} // class Quicksort
