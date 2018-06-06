package com.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageParam {
    private int pageNo = 1;
    private int pageSize = 30;
}
