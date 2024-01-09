package com.team1.jbugger.Events;
import com.team1.jbugger.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
@Builder
public class UserDeactivated {
    private Users deactivatedUser;
}
