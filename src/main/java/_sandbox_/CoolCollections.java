package _sandbox_;

import java.util.*;

/**
 * showcase of different cool features regarding collections
 */
public class CoolCollections {
    public static void main(String[] args) {
        priorityQueue();
        System.out.println("-------");
        mapUpdates();
        System.out.println("-------");
        linkedHashSet();
        System.out.println("-------");
        sub();
        System.out.println("-------");
        collectionsAlgs();
        System.out.println("-------");
        collections();
    }

    private static void priorityQueue() {
        // heap in java
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(List.of(1, 9, 2, 7, 4, 3, 8, 6, 0, 5));
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }

    private static void mapUpdates() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);

        map.merge("one", 1, Integer::sum);

        map.compute("two", (s, integer) -> integer == null ? 666 : integer * 100);
        System.out.println(map);
        map.remove("two");
        map.compute("two", (s, integer) -> integer == null ? 666 : integer * 100);
        System.out.println(map);

        map.replaceAll((s, integer) -> integer - 1);
        System.out.println(map);

        for (var e : map.entrySet()) {
            e.setValue(e.getValue() - 1);
        }
        System.out.println(map);
    }

    private static void linkedHashSet() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        set.add("five");
        set.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    private static void sub() {
        List<Integer> list = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        list.subList(3, 6).clear();
        System.out.println(list);

        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.subMap(2, 4).clear();
        System.out.println(map);
    }

    private static void collections() {
        List<String> copies = Collections.nCopies(10, "STRING");
        System.out.println(copies);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        System.out.println(arrayList);

        var unmodifiableList = Collections.unmodifiableList(arrayList);
        //noinspection DataFlowIssue
        unmodifiableList.add("c");

        var synchronizedList = Collections.synchronizedList(arrayList);
        synchronizedList.add("d");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void collectionsAlgs() {
        var list = new ArrayList<>(List.of("aa", "bb", "cc", "dd"));

        Collections.binarySearch(list, "bb");
        Collections.frequency(list, "bb");

        Collections.reverse(list);
        Collections.shuffle(list);

        Collections.swap(list, 0, 1);
        Collections.rotate(list, 2);

        Collections.sort(list);
        Collections.fill(list, "xx");

        Collections.min(list);
        Collections.max(list);
    }
}
