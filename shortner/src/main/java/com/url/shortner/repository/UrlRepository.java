package com.url.shortner.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(String shortCode, String longUrl){
        String sql= "INSERT INTO url(short_code,long_url) VALUES(?,?)";
        jdbcTemplate.update(sql, shortCode,longUrl);
    }

    public String findLongUrl(String shortCode){
        String sql= "SELECT long_url FROM url WHERE short_code=?";
        try{
            return jdbcTemplate.queryForObject(sql,String.class,shortCode);
        }
        catch(Exception e){
            return null;
        }
    }

    public boolean existsByShortCode(String shortCode){
        String sql= "SELECT COUNT(*) FROM url WHERE short_code= ?";
        Integer count= jdbcTemplate.queryForObject(sql,Integer.class,shortCode);
        if(count!=null && count>0) return true;
        return false;
    }

    public void incrementClickCount(String shortCode){
        String sql="UPDATE url SET click_count= click_count+1 WHERE short_code=?";
        jdbcTemplate.update(sql,shortCode);
    }

    public int getClickCount(String shortCode){
        String sql="SELECT click_count FROM url WHERE short_code=?";
        try{
            return jdbcTemplate.queryForObject(sql,Integer.class,shortCode);
        }
        catch(Exception e){
            return 0;
        }
    }
}
