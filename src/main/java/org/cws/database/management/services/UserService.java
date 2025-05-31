package org.cws.database.management.services;

import org.cws.database.management.models.dtos.UserDTO;
import org.cws.database.management.models.responses.UserResponse;
import org.cws.database.management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BigDecimal saveOrUpdateUser(UserDTO userDTO){
        return userRepository.saveOrUpdateUser(userDTO);
    }

    public List<UserResponse> getUser(BigDecimal cwsUserId, String loginId){
        return userRepository.getUser(cwsUserId, loginId);
    }
}
