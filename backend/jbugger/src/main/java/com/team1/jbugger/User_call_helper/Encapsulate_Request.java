package com.team1.jbugger.User_call_helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Encapsulate_Request {
    String mobileNumber;
    String email;
    List<String> roles;
}

