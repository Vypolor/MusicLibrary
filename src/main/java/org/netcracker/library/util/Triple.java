package org.netcracker.library.util;

import java.util.Arrays;
import java.util.Objects;

public class Triple<N, K, A> {
    private N name;
    private K key;
    private A[] args;

    public Triple(N name, K key, A[] args) {
        this.name = name;
        this.key = key;
        this.args = args;
    }

    public N getName() {
        return name;
    }

    public K getKey() {
        return key;
    }

    public A[] getArgs() {
        return args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(name, triple.name) &&
                Objects.equals(key, triple.key) &&
                Arrays.equals(args, triple.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key, args);
    }

    @Override
    public String toString() {
        return "Triple{" +
                "name=" + name +
                ", key=" + key +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
