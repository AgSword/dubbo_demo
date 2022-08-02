package com.example.consumer.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.utils
 * @className: BeanMapper
 * @author: LiYinjian
 * @date: 2022/7/29 9:01
 * @version: 1.0
 */

@Component
public class BeanMapper {
    protected Mapper dozerMapper = new DozerBeanMapper();

    public BeanMapper() {
    }

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    public <T, S> T convert(S s, Class<T> clz) {
        return s == null ? null : this.dozerMapper.map(s, clz);
    }

    public <T, S> List<T> convert(List<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        Iterator iterator = s.iterator();
        while (iterator.hasNext()) {
            S next = (S) iterator.next();
            list.add(dozerMapper.map(next, clz));
        }
        return list;
    }

    public <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        HashSet<T> set = new HashSet<>();
        Iterator iterator = s.iterator();
        while (iterator.hasNext()) {
            S next = (S) iterator.next();
            set.add(dozerMapper.map(next, clz));
        }
        return set;
    }

    public <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        T[] arr = (T[]) Array.newInstance(clz, s.length);
        for (int i = 0; i < s.length; i++) {
            arr[i] = this.dozerMapper.map(s[i], clz);
        }
        return arr;
    }

    public <T, S> T copy(S s, T t) {
        if (s != null && t != null) {
            this.dozerMapper.map(s, t);
            return t;
        }
        return null;
    }

}
