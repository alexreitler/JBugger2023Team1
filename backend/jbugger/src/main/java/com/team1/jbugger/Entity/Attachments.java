package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    public Attachments(int idAtt, byte[] attContent) {
        this.idAtt = idAtt;
        this.attContent = attContent;
    }
}
