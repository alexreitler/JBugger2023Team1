package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Attachments")
public class Attachments {
    @Id
    @GeneratedValue
    private int idAtt;
    private byte[] attContent;
    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bugs bug;

    public Attachments(byte[] attContent) {
        this.attContent = attContent;
    }
}
