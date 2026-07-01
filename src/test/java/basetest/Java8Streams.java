package basetest;

import baseclass.Users;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Streams {

    @Test
    public void testStreamOperations() {
        // Example of using Java 8 Streams
        int[] numbers = {1, 2, 3, 4, 5};
        int sum1 = java.util.Arrays.stream(numbers)
                                  .filter(n -> n % 2 == 0)
                                  .map(n -> n * n)
                                  .sum();
        System.out.println("Sum of squares of even numbers: " + sum1);

        //01 Filter even numbers
        List<Integer> integerList = List.of(1, 2, 3, 4);
        Set<Integer> evenSet = integerList.stream().filter(n -> n % 2 == 0).collect(Collectors.toSet());
        System.out.println(evenSet);


        //02 Filter strings by length filter
        List<String> name = List.of("Amit", "Samit", "Anita");
        Set<String> stringSet = name.stream().filter(n -> n.length() > 4).collect(Collectors.toSet());
        System.out.println(stringSet);

        //03 Map to uppercase
        Set<String> upperCase =
                name.stream().map(String::toUpperCase).collect(Collectors.toSet());
        System.out.println(upperCase);
        //03 Map to uppercase LESS ADVANCED
        upperCase = name.stream().map(n -> n.toUpperCase()).collect(Collectors.toSet());
        System.out.println(upperCase);

        //04 Map objects to field map
        Users users1 = new Users(1, "Amit", "amitz@com", 40);
        Users users2 = new Users(2, "SAmitS", "amitz@com", 50);
        Users users3 = new Users(3, "ZAmitSs", "amitz@com", 60);
        List<Users> usersList = List.of(users1, users2, users3);
        Set<Integer> userAge = usersList.stream().map(Users::getAge).collect(Collectors.toSet());
        System.out.println(userAge);

        //05 Map to string lengths
        Set<Integer> nameLength = usersList.stream().map(Users::getName).map(n -> n.length()).collect(Collectors.toSet());
        System.out.println(nameLength);
        //advanced
        nameLength = usersList.stream().map(Users::getName).map(String::length).collect(Collectors.toSet());
        System.out.println(nameLength);

        //  06 Reduce to sum reduce
        //advanced
        Integer sumOfAge = usersList.stream().map(Users::getAge).reduce(0, Integer::sum);
        System.out.println(sumOfAge);

        sumOfAge = usersList.stream().map(Users::getAge).reduce(0, (a, b) -> a + b);
        System.out.println(sumOfAge);

        //07 Reduce to product
        Integer productOfAge = usersList.stream().map(Users::getAge).reduce(1, (a, b) -> a * b);
        System.out.println(productOfAge);

        //08 Reduce to max string
        Optional<String> maxLength = usersList.stream().map(Users::getName).reduce((a, b) -> a.length() > b.length() ? a : b);
        System.out.println(maxLength.get());

        //09 Collect to Set collect
        List<Integer> integers = List.of(1, 1, 2, 2, 3, 3);
        Set<Integer> integerSet = integers.stream().collect(Collectors.toSet());
        System.out.println(integerSet);

        //10 Collect to Map
        Map<String, String> map = usersList.stream().collect(Collectors.toMap(Users::getName, Users::getEmail));
        System.out.println(map);

        /* groupingBy(
    Function<? super T, ? extends K> classifier,
    Collector<? super T, A, D> downstream
       ) */

        //11 groupingBy category
        Map<String, List<Users>> emailGroup = usersList.stream().collect(Collectors.groupingBy(Users::getEmail));
        //If User class doesnot have To Reference, its prints object stack value
        System.out.println("Email group with user" + emailGroup);


        // 12 counting with groupingBy
        Map<String, Long> emailGroupCount = usersList.stream().collect(Collectors.groupingBy(Users::getEmail,
                Collectors.counting()));
        System.out.println(emailGroupCount);

        //13 partitioningBy
        List<Integer> oddEven = List.of(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> booleanListMap = oddEven.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(booleanListMap);

        //14 joining strings
        List<String> fullName = List.of("Amit", "Singh");
        //Collectors.joining(delimiter, prefix, suffix)
        //delimiter → inserted between elements
        //prefix → added at the beginning
        //suffix → added at the end
        String result = fullName.stream().collect(Collectors.joining("---", "M", "R"));
        System.out.println(result);

        //15 Average of integers
        OptionalDouble avg = oddEven.stream().mapToInt(Integer::intValue).average();
        System.out.println(avg);

        //16 Sum with mapToInt stat
        int sum = oddEven.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        //17 IntStream statistics
        IntSummaryStatistics stats = oddEven.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println(stats);


        //18 Average
        OptionalDouble avgAge = usersList.stream()
                .mapToInt(Users::getAge).average();
        System.out.println(avgAge);

        //19 Sorted natural order
        List<Integer> unsortedInteger = List.of(11, 10, 21);
        List<Integer> sortedInteger = unsortedInteger.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedInteger);

        sortedInteger = unsortedInteger.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(sortedInteger);

        //20 Sorted reverse order
        sortedInteger = unsortedInteger.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sortedInteger);

        //21 Sorted by field
        List<Users> sortedUser =
                usersList.stream().sorted(Comparator.comparingInt(Users::getAge)).collect(Collectors.toList());

        System.out.println(sortedUser);
        sortedUser = usersList.stream().sorted(Comparator.comparingInt(Users::getAge).reversed()).collect(Collectors.toList());
        System.out.println(sortedUser);

        //22 Multi-level sort sort
        List<Users> doublesortedUser=
                usersList.stream().sorted(Comparator.comparingInt(Users::getAge).thenComparing(Comparator.comparing(Users::getName).reversed())).collect(Collectors.toList());
        System.out.println(doublesortedUser);

        //23 Min element
        List<Integer> list=List.of(1,2,3,4);
        Optional<Integer> optionalInteger=list.stream().min(Comparator.naturalOrder());
        System.out.println(optionalInteger.get());

        Optional<Users> optionalUsers=usersList.stream().min(Comparator.comparing(Users::getAge));
        System.out.println(optionalUsers.get());

        //24 Max element
        optionalInteger=list.stream().max(Comparator.naturalOrder());
        System.out.println(optionalInteger.get());

        optionalUsers=usersList.stream().max(Comparator.comparing(Users::getAge));
        System.out.println(optionalUsers.get());
    }
}
