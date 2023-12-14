package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
public class Comments {
    @Id
    @GeneratedValue
    private int idComment;
    private String text;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bugs bug;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    public Comments(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }
}
