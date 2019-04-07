package com.example.demo.querydsl;

import com.example.demo.jpa.QUserEntity;
import com.example.demo.jpa.UserEntity;
import com.querydsl.jpa.JPQLQuery;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(UserEntity.class);
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Lazy
    @Override
    public List findAllLike(String keyword) {
        HikariDataSource
        QUserEntity qUserEntity = QUserEntity.userEntity;
        JPQLQuery<UserEntity> query = from(qUserEntity);
        List<UserEntity> resultList = query.where(qUserEntity.username.like(keyword)).fetch();
        return resultList;
    }
}
