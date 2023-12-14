package com.team1.jbugger.Mapper;
import com.team1.jbugger.Entity.Users;
import com.team1.jbugger.Dto.UsersDto;

public class UsersMapper {
    public static UsersDto mapToUsersDto(Users user)
    {
        return new UsersDto(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus()
        );
    }
    public static Users mapToUsers(UsersDto usersDto)
    {
        return new Users(
                usersDto.getIdUser(),
                usersDto.getFirstName(),
                usersDto.getLastName(),
                usersDto.getMobileNumber(),
                usersDto.getEmail(),
                usersDto.getUsername(),
                usersDto.getPassword(),
                usersDto.getStatus()
        );
    }
}
