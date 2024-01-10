//package com.team1.jbugger.Entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "User_Notification")
//public class User_Notification
//{
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "idNotification")
//    private Notifications notification;
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private Users user;
//
//    public User_Notification(LocalDate date, Users user, Notifications notification) {
//        this.date = date;
//        this.user = user;
//        this.notification = notification;
//    }
//}
