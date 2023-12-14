package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue
    private int idHistory;
    private LocalDate modifiedDate;
    private String afterStatus;
    private String beforeStatus;
    private String modifiedBy;
    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bugs bug;

    public History(int idHistory, LocalDate modifiedDate, String afterStatus, String beforeStatus, String modifiedBy) {
        this.idHistory = idHistory;
        this.modifiedDate = modifiedDate;
        this.afterStatus = afterStatus;
        this.beforeStatus = beforeStatus;
        this.modifiedBy = modifiedBy;
    }
}
