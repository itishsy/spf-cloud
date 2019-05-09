package com.seebon.oauth.service.impl;

import com.seebon.oauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static final String BASE_FIND_STATEMENT = "SELECT res.`uri_pattern` FROM `sys_resource` res JOIN `sys_privilege` pri ON res.`id` = pri.`resource_id`";

    private static final String BASE_JOIN_ROLE_STATEMENT = BASE_FIND_STATEMENT + " JOIN `sys_role` rol ON rol.`id` = pri.`role_id`";

    private static final String BASE_JOIN_USER_STATEMENT = BASE_JOIN_ROLE_STATEMENT + " JOIN `sys_user_role` uro ON uro.`role_id` = rol.`id` JOIN `sys_user` usr ON usr.`id` = uro.`user_id`";

    private static final String DEFAULT_SELECT_BY_ROLE_STATEMENT = BASE_JOIN_ROLE_STATEMENT + " WHERE rol.`code` = ?";

    private static final String DEFAULT_SELECT_BY_USER_STATEMENT = BASE_JOIN_USER_STATEMENT + " WHERE usr.`username` = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public JdbcTemplate getJdbcTemplate() {
        if(jdbcTemplate == null){
             jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    @Override
    public List<String> findResourceByUsername(String username) {
        return getJdbcTemplate().queryForList(DEFAULT_SELECT_BY_USER_STATEMENT,String.class,username);
    }

    @Override
    public List<String> findResourceByRole(String role) {
        return getJdbcTemplate().queryForList(DEFAULT_SELECT_BY_ROLE_STATEMENT,String.class,role);
    }
}
