package io.vincentwinner.toolset.domain.unary;

import java.io.Serializable;
import java.util.Objects;

/**
 * 一元数
 */
public class Unary implements Serializable {

    private static final long serialVersionUID = 5995022237526166654L;

    private Double x;

    public Unary() {
        this.x = 0.0;
    }

    public Unary(Double x) {
        this.x = x;
    }

    public strictfp Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Long floor(){
        return x.longValue();
    }

    public Long ceil(){
        long floor = floor();
        return x - floor > 0 ? floor + 1 : floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unary unary = (Unary) o;
        return Objects.equals(x, unary.x);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(x);
    }

}
