package com.cjq.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.*;

/**
 * 布隆过滤器，一个很长的二进制向量跟随机个哈希函数
 * 快速判断某个数据是否在海量数据中
 * 储存跟查询速度很快，存在一定误报，删除元素困难
 * Created by jq Chen on 2018/8/22.
 */
public class BloomFilterTest {

    private static final int insertions = 1000000;

    @Test
    public void testBf() {
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);
        Set<String> set = new HashSet<>(insertions);
        List<String> list = new ArrayList<>(insertions);

        for(int i=0; i<insertions; i++) {
            String value = UUID.randomUUID().toString();
            bf.put(value);
            set.add(value);
            list.add(value);
        }

        int right = 0;//成功命中的数量
        int wrong = 0;//误报的数量

        for(int i=0;i<10000;i++) {
            String key = i%100 == 0 ? list.get(i/100) : UUID.randomUUID().toString();
            if(bf.mightContain(key)) {
                if(set.contains(key)) {
                    right ++;
                }else {
                    wrong ++;
                }
            }
        }

        System.out.println("=======right====== " + right);
        System.out.println("=======wrong====== " + wrong);
    }
}
