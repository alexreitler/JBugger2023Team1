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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private Set<Attachments> attachments = new HashSet<>();

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<History> history = new HashSet<>();

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comments> comments = new HashSet<>();

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
    public String bugInfo() {
        return String.format("#%s, Title = '%s', Version = '%s', Severity = '%s'",
                getIdBug(), title, version, severity);
    }

    public void setAttachments(Set<Attachments> attachments) {
        this.attachments = attachments;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }
}
