package com.kyu.boot.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_project
 * @Date : 2017-07-21
 * @Author : nklee
 * @Description :
 */
public class StreamAPI {

    List<String> words = new ArrayList<>();

    @Before
    public void before() {
        words.add("test");
        words.add("test1111");
        words.add("test2222");
        words.add("test3333333333333333");
    }

    /**
     * 스트림 연산들은 요소를 대상으로 실행될 때 스트림에서 호출된 순서로 실행되지 않는다.
     * 예제에서는 count()가 호출되기 전에는 아무 일도 일어나지 않는다. count 메서드가 첫 번째 요소를 요청하면
     * filter 메서드가 길이 > 12인 요소를 찾을 때까지 요소들을 요청하기 시작한다.
     */
    @Test
    public void 반복에서스트림연산으로() {
        // 반복
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        assertThat(1, is(count));

        // 스트림
        long streamCnt = words.stream().filter(w -> w.length() > 12).count();
        assertThat(1L, is(streamCnt));

        // 스트림 병렬 처리
        streamCnt = words.parallelStream().filter(w -> w.length() > 12).count();
        assertThat(1L, is(streamCnt));
    }

    @Test
    public void 스트림생성() {
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        System.out.println(song);

    }
}
