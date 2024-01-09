package com.team1.jbugger.User_call_helper;

import com.team1.jbugger.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Update_Response {
    Users user;
}
