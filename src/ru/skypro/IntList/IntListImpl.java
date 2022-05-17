package ru.skypro.IntList;

import ru.skypro.Exception.IndexOutBoundsException;
import ru.skypro.Exception.NotFoundException;

import java.util.Arrays;

public class IntListImpl implements IntList {

    private int[] array = new int[10];
    private int indexOfElement = 0;

    private void increaseSizeOfArray(int addingSize) {
        int[] tempArray = Arrays.copyOf(array, array.length);
        this.array = new int[indexOfElement + addingSize];
        array = Arrays.copyOf(tempArray, array.length);
    }

    @Override
    public int add(int item) {
        if ((indexOfElement) == array.length) {
            increaseSizeOfArray(1);
        }
        array[indexOfElement] = item;
        indexOfElement++;
        return item;
    }

    @Override
    public int add(int index, int item) {
        if ((indexOfElement) == array.length) {
            increaseSizeOfArray(1);
        }
        if (index >= indexOfElement) {
            throw new IndexOutBoundsException();
        }
        for (int i = indexOfElement - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = item;
        indexOfElement++;
        return item;
    }

    @Override
    public int set(int index, int item) {
        if (index >= indexOfElement || index >= array.length) {
            throw new IndexOutBoundsException();
        }
        array[index] = item;
        return item;
    }

    @Override
    public int remove(int item) {
        int indexOfSearchableElement = indexOf(item);
        if (indexOfSearchableElement == -1) {
            throw new NotFoundException();
        }
        for (int j = indexOfSearchableElement; j < indexOfElement - 1; j++) {
            array[j] = array[j + 1];
        }
        array[indexOfElement - 1] = 0;
        indexOfElement--;
        return item;
    }

    @Override
    public int removeByIndex(int index) {
        if (index >= indexOfElement) {
            throw new IndexOutBoundsException();
        }
        int removedElement = array[index];
        for (int i = index; i < indexOfElement - 1; i++) {
            array[i] = array[i + 1];
        }
        array[indexOfElement - 1] = 0;
        indexOfElement--;
        return removedElement;
    }

    @Override
    public boolean contains(int item) {
        sortInsertion(array);
        return contains(array, item);
    }

    @Override
    public int indexOf(int item) {
        int indexOfSearchableElement = -1;
        for (int i = 0; i < indexOfElement; i++) {
            if (array[i] == item) {
                indexOfSearchableElement = i;
                break;
            }
        }
        return indexOfSearchableElement;
    }

    @Override
    public int lastIndexOf(int item) {
        int indexOfSearchableElement = -1;
        for (int i = indexOfElement - 1; i >= 0; i--) {
            if (array[i] == item) {
                indexOfSearchableElement = i;
                break;
            }
        }
        return indexOfSearchableElement;
    }

    @Override
    public int get(int index) {
        if (index >= indexOfElement) {
            throw new IndexOutBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        boolean result = true;
        if (otherList.size() != indexOfElement) {
            result = false;
            return result;
        }
        for (int i = 0; i < indexOfElement; i++) {
            if (!(array[i] == otherList.get(i))) {
                result = false;
                return result;
            }
        }
        return result;
    }

    @Override
    public int size() {
        return indexOfElement;
    }

    @Override
    public boolean isEmpty() {
        return indexOfElement == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < indexOfElement; i++) {
            array[i] = 0;
        }
        indexOfElement = 0;
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(array, indexOfElement);
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static boolean contains(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}