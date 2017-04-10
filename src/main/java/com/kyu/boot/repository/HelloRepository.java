package com.kyu.boot.repository;

import com.kyu.boot.entity.NamkyuUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nklee on 2017-04-10.
 */
public interface HelloRepository extends JpaRepository<NamkyuUser, Long> {

}
