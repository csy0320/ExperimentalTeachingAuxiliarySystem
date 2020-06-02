package cn.jjdcn.soa.etas.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchParam {

    private String keyword;//检索的关键字

    private Integer pageNum = 1;//分页信息

    private Integer pageSize = 2;

}
