package com.WorkAttendance.common.page;

import java.util.Collections;
import java.util.List;

/**
 * @Author fangtongle
 * @Date 2017/8/21 21:53
 * @Description 分页Bean
 **/
public class PageQueryBean {

    private static final int DEFAULT_PAGE_SIZE = 10;
    // 当前页
    private Integer currentPage;

    // 每页显示的数据
    private Integer pageSize;

    // 所有记录数目
    private int totalRows;

    // 起始页
    private Integer startRow;

    // 总页数
    private Integer totalPage;

    // 结果集
    private List<?> items;

    public final Integer getStartRow() {
        if (startRow == null) startRow = (currentPage == null ? 0 : (currentPage - 1) * getPageSize());
        return startRow;
    }


    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public final Integer getPageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public final void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public final int getTotalRows() {
        return totalRows;
    }

    public final void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        int totalPage = totalRows % getPageSize() == 0 ? totalRows / getPageSize() : totalRows / getPageSize() + 1;
        setTotalPage(totalPage);
    }

    public final List<?> getItems() {
        return items == null ? Collections.EMPTY_LIST : items;
    }

    public final void setItems(List<?> items) {
        this.items = items;
    }

    public final Integer getCurrentPage() {
        return currentPage;
    }

    public final void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public final Integer getTotalPage() {
        return totalPage == null || totalPage == 0 ? 1 : totalPage;
    }

    public final void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


    @Override
    public String toString() {
        return "PageQueryBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalRows=" + totalRows + ", startRow=" + startRow + ", totalPage=" + totalPage + ", items=" + items + "]";
    }

}
