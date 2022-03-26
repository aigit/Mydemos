package org.study.functional;

import lombok.extern.slf4j.Slf4j;
import org.study.functional.model.Artist;
import org.study.functional.model.Singer;
import org.study.functional.model.Track;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DemoTest {

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        //demoTest.testPredicate();
        //demoTest.testThreadLocal();
        //demoTest.testMaxStream();
        //demoTest.testStreamSum();
        demoTest.testFlatMap();
    }

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

}
