package io.vincentwinner.toolset.core.domain.unary;

import io.vincentwinner.toolset.core.domain.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 一元域
 */
public class UnaryDomain implements Domain<Unary>,Serializable {

    private static final long serialVersionUID = 4296168878061789187L;

    /**
     * 一元基本域
     * max   域最大值
     * min   域最小值
     * span  域跨度
     */
    public static class UnaryBaseDomain implements Domain<Unary>,Serializable{
        private static final long serialVersionUID = 5814067226191064632L;
        private Unary min;
        private Unary max;
        private double span;
        public UnaryBaseDomain(){}
        public UnaryBaseDomain(Unary min, Unary max){
            this.min = min;
            this.max = max;
            this.span = max.getX() - min.getX();
        }
        public UnaryBaseDomain(Double min, Double max){
            this.min = new Unary(min);
            this.max = new Unary(max);
            this.span = max - min;
        }
        public Unary getMin() {
            return min;
        }
        public void setMin(Unary min) {
            this.min = min;
            this.span = this.max.getX() - min.getX();
        }
        public void setMin(Double min){
            this.min = new Unary(min);
            this.span = this.max.getX() - min;
        }
        public Unary getMax() {
            return max;
        }
        public void setMax(Unary max) {
            this.max = max;
            this.span = this.max.getX() - min.getX();
        }
        public void setMax(Double max){
            this.max = new Unary(max);
            this.span = max - min.getX();
        }
        public double getSpan() {
            return span;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnaryBaseDomain that = (UnaryBaseDomain) o;
            return Objects.equals(min, that.min) && Objects.equals(max, that.max);
        }

        @Override
        public String toString() {
            return "[" + min + " ~ " + max + "]";
        }

        @Override
        public boolean isInDomain(Unary element) {
            return element.getX() >= min.getX() && element.getX() <= max.getX();
        }
    }

    private List<UnaryBaseDomain> domainList;
    private int size;

    public UnaryDomain(){
        domainList = new ArrayList<>();
    }

    public UnaryDomain(UnaryDomain... unaryDomains){
        List<UnaryBaseDomain> result = new ArrayList<>(unaryDomains.length << 1);
        for(UnaryDomain domain : unaryDomains){
            result.addAll(domain.getDomainList());
        }
        this.domainList = result;
        this.size = calcSize(result);
    }

    public UnaryDomain(UnaryBaseDomain... domains){
        this.domainList = Arrays.asList(domains);
        this.size = calcSize(Arrays.asList(domains));
    }

    public UnaryDomain(List<UnaryBaseDomain> domains){
        this.domainList = domains;
        this.size = calcSize(domains);
    }

    public List<UnaryBaseDomain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<UnaryBaseDomain> domainList) {
        this.domainList = domainList;
        this.size = calcSize(domainList);
    }

    public void addDomain(UnaryBaseDomain baseDomain){
        this.domainList.add(baseDomain);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    /**
     * 计算域跨度
     */
    private int calcSize(List<UnaryBaseDomain> domains){
        return domains.stream().mapToInt(domain -> (int) domain.getSpan()).sum();
    }

    @Override
    public boolean isInDomain(Unary element) {
        for(UnaryBaseDomain domain : domainList){
            if(domain.isInDomain(element)){
                return true;
            }
        }
        return false;
    }

}
