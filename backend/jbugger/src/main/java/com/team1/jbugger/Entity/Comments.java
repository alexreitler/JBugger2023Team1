package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idComment;
    private String text;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bugs bug;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    public Comments(int idComment, String text, LocalDate date) {
        this.idComment = idComment;
        this.text = text;
        this.date = date;
    }
}
