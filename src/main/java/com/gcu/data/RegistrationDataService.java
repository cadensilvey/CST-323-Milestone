package com.gcu.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.RegistrationModel;

@Primary
@Repository
public class RegistrationDataService implements RegistrationDataAccessInterface {



    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public long addOne(RegistrationModel newUser) {
        return jdbcTemplate.update("insert into USERS (USERNAME, PASSWORD) values (?,?)",
            newUser.getUsername(), newUser.getPassword());
    }

   

    


    

   
    
}
