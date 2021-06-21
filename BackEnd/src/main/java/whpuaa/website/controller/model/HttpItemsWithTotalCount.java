package whpuaa.website.controller.model;


import whpuaa.website.util.ListWithTotalCount;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HttpItemsWithTotalCount<T> {
    private long totalCount;
    private List<T> items;

    public HttpItemsWithTotalCount() {

    }

    public HttpItemsWithTotalCount(long totalCount, List<T> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    public HttpItemsWithTotalCount(ListWithTotalCount<T> listWithTotalCount) {
        this.totalCount = listWithTotalCount.getTotalCount();
        this.items = listWithTotalCount.getList();
    }

    public <S> HttpItemsWithTotalCount(ListWithTotalCount<S> listWithTotalCount, Function<? super S, ? extends T> mapper) {
        this.totalCount = listWithTotalCount.getTotalCount();
        this.items = listWithTotalCount.getList().stream().map(mapper).collect(Collectors.toList());
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
