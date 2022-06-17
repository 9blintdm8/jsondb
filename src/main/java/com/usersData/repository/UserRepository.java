package com.usersData.repository;

import com.usersData.domain.UserClass;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserClass,Long> {

}
