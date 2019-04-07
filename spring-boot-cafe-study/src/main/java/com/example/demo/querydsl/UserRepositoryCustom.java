package com.example.demo.querydsl;

import java.util.List;

public interface UserRepositoryCustom {

    List findAllLike(String keyword);

}
