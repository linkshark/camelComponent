package com.linkjb.camelcomponent.service;

import com.linkjb.camelcomponent.common.BaseResult;
import com.linkjb.camelcomponent.dto.JdbcDTO;

public interface ComponentTestService {
    BaseResult testJdbc(JdbcDTO jdbcDTO) throws Exception;

    BaseResult testSplit(String body);
}
