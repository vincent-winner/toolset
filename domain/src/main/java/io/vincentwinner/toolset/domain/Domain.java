package io.vincentwinner.toolset.domain;

public interface Domain<T> {

    boolean isInDomain(T element);

}
