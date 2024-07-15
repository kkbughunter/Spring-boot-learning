package com.sample.demoapp.controller;

//import com.sample.demoapp.Dto.UserDTO;
import com.sample.demoapp.model.UserModel;
import com.sample.demoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    // DTO Code
//    @GetMapping("/{email}")
//    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
//        UserDTO userDTO = userService.findByEmail(email);
//        return ResponseEntity.ok(userDTO);
//    }

     // Normal Code
    @GetMapping("/{email}")
    public ResponseEntity<UserModel> getUserByEmail(@PathVariable String email) {
         UserModel userModel = userService.findByEmail(email);
         System.out.println(userModel);
        return ResponseEntity.ok(userModel);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserModel userModel){
        boolean result = userService.addNewUser(userModel);
        if(result)
            return ResponseEntity.ok("User Added Successfully");
        else
            return ResponseEntity.ok("User Fail to Add");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        boolean result = userService.deleteByEmail(email);
        if (result)
            return ResponseEntity.ok("User Deleted Successfully.");
        else
            return ResponseEntity.ok("User Delet Failed.");

    }


    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> payload){
        boolean result = userService.updatePassword(payload.get("email"), payload.get("newPassword"));
        if (result) {
            return ResponseEntity.ok("Password Updated Successfully.");
        } else {
            return ResponseEntity.status(404).body("User Not Found.");
        }
    }

}
