package com.team1.jbugger.Service;

import com.team1.jbugger.Dto.UsersDto;
import com.team1.jbugger.Mapper.UsersMapper;
import com.team1.jbugger.Repository.UsersRepository;
import com.team1.jbugger.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    public final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(UsersMapper::mapToUsersDto)
                .collect(Collectors.toList());
    }

    public UsersDto getUserById(int id) {
        return usersRepository.findById((long) id)
                .map(UsersMapper::mapToUsersDto)
                .orElse(null);
    }

    public Users saveUser(UsersDto usersDto) {
        Users user = UsersMapper.mapToUsers(usersDto);
        return usersRepository.save(user);
    }

    public Users updateUser(Users user, int id) {
        Users userToUpdate = usersRepository.findById((long) id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setMobileNumber(user.getMobileNumber());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setStatus(user.getStatus());
            return usersRepository.save(userToUpdate);
        }
        return usersRepository.save(user);
    }

    public void deleteUser(int id) {
        usersRepository.deleteById((long) id);
    }
}
