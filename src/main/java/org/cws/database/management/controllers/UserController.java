package org.cws.database.management.controllers;

import org.cws.database.management.models.dtos.UserDTO;
import org.cws.database.management.models.responses.UserResponse;
import org.cws.database.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUser(@RequestParam (required = false) BigDecimal cwsUserId,
                                                      @RequestParam (required = false) String loginId){
        var resp = userService.getUser(cwsUserId, loginId);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BigDecimal> saveOrUpdateUser(@RequestBody UserDTO userDTO){
        var resp = userService.saveOrUpdateUser(userDTO);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
