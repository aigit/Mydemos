package org.study.functional;

import lombok.extern.slf4j.Slf4j;
import org.study.functional.model.Artist;
import org.study.functional.model.Singer;
import org.study.functional.model.Track;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class DemoTest {



    private void testPredicate(){
        Predicate<Integer> integerPredicate = x->x>5;
        final boolean test = integerPredicate.test(6);
        Integer a= 8;
        IntPred intPred = x->x>5;
        log.info("预判:{}",intPred.test(a));
    }

    private void testThreadLocal(){
        ThreadLocal<DateFormat> dateFormatThreadLocal =
                ThreadLocal.withInitial(()->{return new SimpleDateFormat("yyyy-MM-dd");});
        final String format = dateFormatThreadLocal.get().format(new Date());
        log.info("date :{}",format);
    }

    private void testMaxStream(){
        List<Track> trackList = new ArrayList<>();
        trackList.add(new Track("a",12));
        trackList.add(new Track("c",5));
        trackList.add(new Track("b",36));
        final Track track = trackList.stream().
                max(Comparator.comparing(t->t.getTimes())).get();

        log.info("最大时长的专辑:{}",track.getName());
    }

    private void testStreamSum(){
        final Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        log.info("求和:{}",sum(integerStream));
    }

    private void testFlatMap(){
        List<Artist> artists = new ArrayList<>();
        Stream<Singer> singerStream1 = Stream.of(new Singer("a",1),new Singer("b",2),new Singer("c",3));
        Artist artist1 = new Artist();
        artist1.setSingers(singerStream1.collect(Collectors.toSet()));
        artists.add(artist1);
        Stream<Singer> singerStream2 = Stream.of(new Singer("d",4),new Singer("e",5));
        Artist artist2 = new Artist();
        artist2.setSingers(singerStream2.collect(Collectors.toSet()));
        artists.add(artist2);

        final long count = artists.stream().flatMap(a -> a.getSingers().stream()).count();
        log.info("共有{}个歌唱者",count);
    }

    private int sum(Stream<Integer> numbers){
        return numbers.reduce(0,(x,y)->x+y).intValue();
    }


    private void testCountLowerChar(){
        String mine = "abCDeFGhiJK";
        final long count = mine.chars().filter(c -> (c>96 && c < 124)).count();
        log.info("小写字母个数:{},a:{}",count,(int)'a');
    }

    private void findMostLowerCharString(){
        List<String> fromStringList = new ArrayList<>();
        fromStringList.add("abC");
        fromStringList.add("aBC");
        fromStringList.add("abcD");
        fromStringList.add("abcdE");
        fromStringList.add("abcdeF");
        final Optional<String> max = fromStringList.stream().max(Comparator.comparing(s -> (s.chars().filter(c -> (c > 96 && c < 124)).count())));

        log.info("最多小字母的:{}",max.orElse("没找到"));

    }

    private void reduceMakeMap(){
        List<String> fromStringList = new ArrayList<>();
        fromStringList.add("abC");
        fromStringList.add("aBC");
        fromStringList.add("abcD");
        fromStringList.add("abcdE");
        fromStringList.add("abcdeF");
        final List<String> collect = fromStringList.stream().
                map(s -> s.toUpperCase()).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }

    private void testMapToNumbers(){
        final Stream<Integer> integerStream = Stream.of(1, 3, 4, 5, 2, 6, 7, 8, 9, 10);
        final IntSummaryStatistics intSummaryStatistics = integerStream.mapToInt(x -> x).summaryStatistics();
        log.info("max:{},min:{},aver:{},sum:{}",
                intSummaryStatistics.getMax(),
                intSummaryStatistics.getMin(),
                intSummaryStatistics.getAverage(),
                intSummaryStatistics.getSum());
    }

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        //demoTest.testPredicate();
        //demoTest.testThreadLocal();
        //demoTest.testMaxStream();
        //demoTest.testStreamSum();
        //demoTest.testFlatMap();
        //demoTest.testCountLowerChar();
        //demoTest.findMostLowerCharString();
        //demoTest.reduceMakeMap();
        demoTest.testMapToNumbers();
    }
}
