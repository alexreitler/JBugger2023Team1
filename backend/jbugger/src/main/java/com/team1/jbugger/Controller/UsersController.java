//package com.team1.jbugger.Controller;
//
//
//import com.team1.jbugger.Dto.UsersDto;
//import com.team1.jbugger.Entity.Users;
//import com.team1.jbugger.Service.UsersService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UsersController {
//    public final UsersService usersService;
//
//    public UsersController(UsersService usersService) {
//        this.usersService = usersService;
//    }
//
//    @GetMapping("/all")
//    public List<UsersDto> getAllUsers() {
//        return usersService.getAllUsers();
//    }
//
//    @GetMapping("/getById/{id}")
//    public UsersDto getUserById(@PathVariable int id) {
//        return usersService.getUserById(id);
//    }
//
//    @PostMapping("/add")
//    public Users saveUser(@RequestBody UsersDto usersDto) {
//        return usersService.saveUser(usersDto);
//    }
//
//    @PutMapping("/update/{id}")
//    public Users updateUser(@RequestBody Users user, @PathVariable int id) {
//        return usersService.updateUser(user, id);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteUser(@PathVariable int id) {
//        usersService.deleteUser(id);
//    }
//}
