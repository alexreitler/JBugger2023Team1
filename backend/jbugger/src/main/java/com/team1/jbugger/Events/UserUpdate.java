package com.team1.jbugger.Events;

import com.team1.jbugger.Entity.Users;
import com.team1.jbugger.User_call_helper.User_Information;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
@Builder
public class UserUpdate {
    private Users user_updated;
    private Users user_new;//idk a better name
    private User_Information previous_info;


}