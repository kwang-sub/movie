package com.example.movie.util.pagine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Data
@AllArgsConstructor
@ToString
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
        type = "코미디";
    }

    public Pageable getPageable() {
        return PageRequest.of(page - 1, size);
    }
}
