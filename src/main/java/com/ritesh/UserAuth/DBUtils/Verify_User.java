package com.ritesh.UserAuth.DBUtils;

import com.ritesh.UserAuth.Model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Log
public class Verify_User {
    // code to implement login logic
    @Autowired
    private final User user;

    @Autowired
    private final DataSource dataSource;

    public Verify_User(User user, DataSource dataSource) {
        this.user = user;
        this.dataSource = dataSource;
    }

    public Boolean verifyUser()
    {
        if(user==null || dataSource==null)
        {
            log.info("User Entity or dataSource is Empty ");
            return false;
        }
        try {
            JdbcTemplate temp = new JdbcTemplate(dataSource);
            String query = "SELECT EXISTS(Select 1 FROM register WHERE Email=? AND password=?)";
            return temp.queryForObject(query, Boolean.class, user.getEmail_Id(), user.getPassword());
        }catch(Exception e){
            log.warning("Sql Exceptrion :"+e);
            return false;
        }
    }
}
