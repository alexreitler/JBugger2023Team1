package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.team1.jbugger.Enums.BugStatus;
import com.team1.jbugger.Enums.BugSeverity;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bugs")
public class Bugs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBug;
    private String title;
    private String description;
    private String version;
    private LocalDate targetDate;;
    private BugStatus status;
    private String fixedVersion;
    private BugSeverity severity;

    @ManyToOne
    @JoinColumn(name = "createdByUser")
    private Users userCreated;

    @ManyToOne
    @JoinColumn(name = "assignedTo")
    private Users userAssigned;

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachments> attachments = new ArrayList<>();

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachments> history = new ArrayList<>();

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachments> comments = new ArrayList<>();

    public Bugs(int idBug, String title, String description, String version, LocalDate targetDate, BugStatus status, String fixedVersion, BugSeverity severity) {
        this.idBug = idBug;
        this.title = title;
        this.description = description;
        this.version = version;
        this.targetDate = targetDate;
        this.status = status;
        this.fixedVersion = fixedVersion;
        this.severity = severity;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }

    public void setHistory(List<Attachments> history) {
        this.history = history;
    }

    public void setComments(List<Attachments> comments) {
        this.comments = comments;
    }
}
