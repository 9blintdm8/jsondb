package com.users_data.repository;

import com.users_data.domain.UserClass;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserClass,Long> {

}
