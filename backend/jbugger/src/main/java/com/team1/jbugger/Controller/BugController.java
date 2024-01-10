package com.team1.jbugger.Controller;

import com.team1.jbugger.Service.BugService;
import com.team1.jbugger.Bug_Calls.*;
import com.team1.jbugger.Enums.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@RestController
@PreAuthorize("hasAnyRole('ADM', 'TM', 'DEV')")
@RequestMapping("/api/bug")
public class BugController {

    private final BugService bugService;

    @Autowired
    public BugController(BugService bugService)
    {
        this.bugService = bugService;
    }


    @GetMapping("/{idBug}")
    public ResponseEntity<BugContent> getBugById(@PathVariable long idBug) {
        var bugGetByIdResponse = bugService.getBugById(idBug);
        return bugGetByIdResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchBugs(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String version,
            @RequestParam String fixedRevision,
            @RequestParam String targetDate,
            @RequestParam String status,
            @RequestParam String severity,
            @RequestParam String reporterUsername,
            @RequestParam String assigneeUsername
    ) {
        var request = SearchRequest
                .builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .title(Objects.equals(title, "") ? null : title)
                .description(Objects.equals(description, "") ? null : description)
                .version(Objects.equals(version, "") ? null : version)
                .targetDate(null)
                //.status(BugStatus.valueOf(status))
                .status(null)
                .fixedVersion(Objects.equals(fixedRevision, "") ? null : fixedRevision)
                //.targetDate(new SimpleDateFormat("MM-dd-yyyy").parse(targetDate))
                //.severity(BugSeverity.valueOf(severity))
                .severity(null)
                .CreatedByUsername(Objects.equals(reporterUsername, "") ? null : reporterUsername)
                .AssignedToUsername(Objects.equals(assigneeUsername, "") ? null : assigneeUsername)
                .build();

        var bugSearchResponse = bugService.searchBugs(request);
        return ResponseEntity.ok(bugSearchResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<BugContent> addBug(@RequestParam String username, @RequestBody BugAddRequest request) {
        var bugAddResponse = bugService.addBug(username, request);
        return ResponseEntity.ok(bugAddResponse);
    }

    @PutMapping("/update/{idBug}")
    public ResponseEntity<BugContent> updateBug(@PathVariable long idBug, @RequestBody BugUpdateRequest request) {
        var bugUpdateResponse = bugService.updateBug(idBug, request);
        return ResponseEntity.ok(bugUpdateResponse);
    }

    @PatchMapping("/close/{idBug}")
    public ResponseEntity<BugContent> closeBug(@PathVariable long idBug) {
        var bugCloseResponse = bugService.closeBug(idBug);
        return ResponseEntity.ok(bugCloseResponse);
    }

    @PatchMapping("/statusUpdate/{idBug}")
    public ResponseEntity<BugContent> updateBugStatus(@PathVariable long idBug, @RequestBody StatusUpdateRequest request) {
        var bugStatusUpdateResponse = bugService.updateBugStatus(idBug, request);
        return ResponseEntity.ok(bugStatusUpdateResponse);
    }

    @PostMapping("/addAttachment/{idBug}")
    public ResponseEntity<ContentAttachment> addAttachment(@PathVariable long idBug, @RequestBody BugAddAttachmentRequest request) {
        var bugAddAttachmentResponse = bugService.addAttachment(idBug, request);
        return ResponseEntity.ok(bugAddAttachmentResponse);
    }


    @DeleteMapping("/deleteAttachment/{bugId}/{idAttachment}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable long bugId, @PathVariable long idAttachment) {
        bugService.deleteAttachment(bugId, idAttachment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
