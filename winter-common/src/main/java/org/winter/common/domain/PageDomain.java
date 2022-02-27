package org.winter.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 在Jpa的分页对象Page,简化Page对象 --自定义分页
 * @param <T>
 */
@Getter
@Setter
public class PageDomain<T> {

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 总条数
     */
    private long totalElements;

    /**
     * 页码
     */
    private int pageNumber;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 分页数据
     */
    private List<T> content;

    public PageDomain<T> toPageDomain(Page<T> page) {
        PageDomain<T> pageDomain = new PageDomain<>();
        pageDomain.setContent(page.getContent());
        pageDomain.setTotalPages(page.getTotalPages());
        pageDomain.setTotalElements(page.getTotalElements());
        pageDomain.setPageNumber(page.getNumber());
        pageDomain.setPageSize(page.getSize());
        return pageDomain;
    }




}
