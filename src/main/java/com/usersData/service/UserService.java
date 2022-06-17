package com.usersData.service;

import com.usersData.domain.UserClass;
import com.usersData.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    private final UserRepository userRepository;
    UserClass user = new UserClass();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserClass> list(){
        return userRepository.findAll();
    }

    public UserClass save(UserClass user_Class_data){
        return userRepository.save(user_Class_data);
    }

    public void save(List<UserClass> usersData) {
        userRepository.saveAll(usersData);
    }
}
