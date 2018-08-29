package com.cjq.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jq Chen on 2018/8/3.
 */
public class TestLambda {

    @Test
    public void test() {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("hh");
            }
        };

        Runnable runnable1 = () -> System.out.println("hh");


        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        List<String> list = Arrays.asList(players);

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        System.out.println("---------------------");
        list.forEach((player) -> System.out.println(player));
        System.out.println("---------------------");
        list.forEach(System.out::println);


        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Arrays.sort(players, (s1, s2) -> s1.compareTo(s2));


        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        //map的作用是将一个对象变换为另外一个
        cost.stream().map(x -> x + x*0.05).forEach(System.out::println);
        //reduce是将所有值合并为一个
        double allCost = cost.stream().map(x -> x+x*0.05).reduce((sum,x) -> sum + x).get();
        System.out.println(allCost);
    }
}
