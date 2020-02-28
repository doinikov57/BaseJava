package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {

    public static void main(String[] args) {
        final int[] ad1 = {2, 2, 2, 3, 1, 2, 3};
        final int[] ad2 = {9, 8};
        System.out.println(minValue(ad1));
        System.out.println(minValue(ad2));

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7, 33));

        System.out.println(list1);
        System.out.println(oddOrEven(list1));
        System.out.println(oddOrEven1(list1));
        System.out.println(list2);
        System.out.println(oddOrEven(list2));
        System.out.println(oddOrEven1(list2));
    }

    private static int minValue(int[] values) {
        return (Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, x) -> (acc + x) * 10)) / 10;
    }

    /*реализовать метод List<Integer> oddOrEven(List<Integer> integers)
     * если сумма всех чисел нечетная - удалить все нечетные,
     * если четная - удалить все четные. Сложность алгоритма должна быть O(N).
     * Optional - решение в один стрим.
     */

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int odd = (integers.stream()
                .reduce(0, (acc, x) -> acc + x)) % 2;
        if (odd == 0) {
            return integers.stream()
                    .filter(x -> x % 2 == 1)
                    .collect(Collectors.toList());
        }
        return integers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
    }

    private static List<Integer> oddOrEven1(List<Integer> integers) {

        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy((p) -> p % 2 == 0));
        return (map.get(false).size() % 2 == 0)? map.get(false): map.get(true);
    }
}
