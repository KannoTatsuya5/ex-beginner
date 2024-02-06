package com.example.exbeginner.repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbeginner.model.Member;

@Repository
public class MemberRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Member> MEMBER_ROW_MAPPER = new BeanPropertyRowMapper<>(Member.class);

    public List<Member> search(String name) {
        System.out.println(name);
        StringBuilder memberQuery = new StringBuilder();
        memberQuery.append("""
                select 
                    name 
                from 
                    members
                where 
                    name like :name
                """);
        SqlParameterSource params = new MapSqlParameterSource().addValue("name", "%" + name + "%");
        List<Member> members = template.query(memberQuery.toString(),params,MEMBER_ROW_MAPPER);
        return members;
    }
}
