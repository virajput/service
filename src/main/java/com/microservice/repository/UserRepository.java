package com.microservice.repository;

/**
 *@author Vijendra
 *
 */

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.microservice.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
	
    protected final static Logger log = LoggerFactory.getLogger(UserRepository.class);
    private JdbcTemplate jdbc;
    	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    
    public List<User> getAll() {
    	return jdbc.query("SELECT * FROM USERS", userMapper);
    }
    
    public User getUserById(long id) {
        return jdbc.queryForObject("SELECT * FROM USERS WHERE id=?", userMapper, id);
    }
    
    public User getUserByNameAndPassword(String username, String password) {
    	return jdbc.queryForObject("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?", userMapper, username, password);
    }

    public List<User> getUsers(long[] ids) {
        String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
        return jdbc.query("SELECT * FROM USERS WHERE id IN (" + inIds + ")", userMapper);
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        	log.info("row count"+rowNum);
            User user = new User();
            user.id = rs.getLong("id");
            user.username = rs.getString("username");
            user.password = rs.getString("password");
            user.avatar = rs.getString("avatar");
            user.email = rs.getString("email");
            user.firstname = rs.getString("firstname");
            user.lastname = rs.getString("lastname");
            user.location = rs.getString("location");
            user.membership = rs.getString("membership");
            user.phone = rs.getString("phone");
            user.role = rs.getString("role");
            user.sex = rs.getString("sex");
            user.status = rs.getString("status");
            user.title = rs.getString("title");
            return user;
        }
    };
    
    /* public UserInfo getUserInfo(String username){
    	String sql = "SELECT u.username name, u.password pass, a.authority role FROM "+
    			     "comp_users u INNER JOIN comp_authorities a on u.username=a.username WHERE "+
    			     "u.enabled =1 and u.username = ?";
    	UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql, new Object[]{username},
    		new RowMapper<UserInfo>() {
	            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	                UserInfo user = new UserInfo();
	                user.setUsername(rs.getString("name"));
	                user.setPassword(rs.getString("pass"));
	                user.setRole(rs.getString("role"));
	                return user;
	            }
        });
    	return userInfo;
    } */
    
} 

