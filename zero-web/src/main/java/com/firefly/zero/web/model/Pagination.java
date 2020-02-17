/**
 * File: Pagination
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 18:12
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

public class Pagination<T> {
    private int curPage;
    private int totalPages;

    @JsonProperty("iTotalRecords")
    private int totalRecords;

    // computed, for dataTable JS plugin
    @JsonProperty("iTotalDisplayRecords")
    private final int totalDisplayRecords;
    private int pageSize;

    @JsonProperty("aaData")
    private List<T> data;

    public Pagination() {
        this(1, 10, 0, Lists.newArrayListWithExpectedSize(0));
    }

    public Pagination(final int curPage, final int pageSize, final int totalRecords, final List<T> data) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalRecords;
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPages() {
        final int mod = (totalRecords % pageSize);
        final int divides = (totalRecords / pageSize);
        setTotalPages((mod == 0) ? divides : (divides + 1));

        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalDisplayRecords() {
        return totalDisplayRecords;
    }

    public static int offset(int curPage, int pageSize) {
        return (curPage - 1) * pageSize;
    }
}
