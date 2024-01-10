package com.team1.jbugger.Service;

import com.team1.jbugger.Entity.*;
import com.team1.jbugger.Enums.*;
import com.team1.jbugger.Repository.*;
import com.team1.jbugger.Bug_Calls.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class BugService {
    private final BugsRepository bugRepository;
    private final UsersRepository userRepository;
    private final AttachmentsRepository attachmentRepository;
    private final HistoryRepository historyRepository;

   @Autowired
    public BugService(BugsRepository bugRepository,
                      UsersRepository userRepository,
                      AttachmentsRepository attachmentRepository,
                      HistoryRepository historyRepository)
   {
        this.bugRepository = bugRepository;
        this.userRepository = userRepository;
        this.attachmentRepository = attachmentRepository;
        this.historyRepository = historyRepository;
    }

    public Optional<BugContent> getBugById(long bugId) {
        return bugRepository.findById(bugId)
                .map(BugContent::fromBug);
    }

    public SearchResponse searchBugs(SearchRequest request) {

        List<Bugs> sortedAndFilteredBugs = bugRepository.findAll().stream()
                .sorted(Comparator.comparing(Bugs::getTargetDate))
                .sorted(Comparator.comparing(Bugs::getSeverity))
                .filter(bug -> request.getTitle() == null || bug.getTitle().contains(request.getTitle()))
                .filter(bug -> request.getDescription() == null || bug.getDescription().contains(request.getDescription()))
                .filter(bug -> request.getVersion() == null || bug.getVersion().contains(request.getVersion()))
                .filter(bug -> request.getFixedVersion() == null || bug.getFixedVersion().contains(request.getFixedVersion()))
                .filter(bug -> request.getTargetDate() == null || bug.getTargetDate().equals(request.getTargetDate()))
                .filter(bug -> request.getStatus() == null || bug.getStatus().equals(request.getStatus()))
                .filter(bug -> request.getSeverity() == null || bug.getSeverity() == request.getSeverity())
                .filter(bug -> request.getCreatedByUsername() == null || bug.getUserCreated().getUsername().equals(request.getCreatedByUsername()))
                .filter(bug -> request.getAssignedToUsername() == null || bug.getUserAssigned().getUsername().equals(request.getAssignedToUsername()))
                .toList();

        int offset = request.getPageNumber() * request.getPageSize();
        int totalNumberOfBugs = sortedAndFilteredBugs.size();
        int totalPages = (int) Math.ceil((double) totalNumberOfBugs / request.getPageSize());

        List<Bugs> bugsOnPage = sortedAndFilteredBugs.stream()
                .skip(offset)
                .limit(request.getPageSize())
                .toList();

        List<BugAttributes> resultingItems = bugsOnPage.stream()
                .map(BugAttributes::fromBug)
                .toList();

        return SearchResponse.builder()
                .items(resultingItems)
                .build();
    }

    public BugContent addBug(String username, BugAddRequest request)
    {

        var bug = saveBug(username, request);
        saveAttachment(bug.getIdBug(), request.getAttachmentContent());

        return BugContent.fromBug(bug);
    }

    public BugContent updateBug(long bugId, BugUpdateRequest request) {
        var previousBugStatus = bugRepository.findById(bugId).orElseThrow().getStatus();
        Bugs bug = overrideBug(bugId, request);

        saveHistory(previousBugStatus, bug);

        return BugContent.fromBug(bug);
    }

    public BugContent closeBug(long bugId) {
        return updateBugStatus(bugId, new StatusUpdateRequest(BugStatus.Closed));
    }

    public BugContent updateBugStatus(long bugId, StatusUpdateRequest request) {
        var bugUpdateRequest = BugUpdateRequest.builder()
                .status(request.getStatus())
                .build();
        return updateBug(bugId, bugUpdateRequest);
    }

    public ContentAttachment addAttachment(long bugId, BugAddAttachmentRequest request) {
        var attachment = saveAttachment(bugId, request.getAttachmentContent());

        return ContentAttachment.fromAttachment(attachment);
    }

    public void deleteAttachment(long bugId, long attachmentId) {
        var attachment = attachmentRepository.findById(attachmentId).orElseThrow();

        if (attachment.getBug().getIdBug() != bugId)
            throw new RuntimeException("The attachment does not belong to the bug.");

        attachmentRepository.delete(attachment);
    }

    Bugs saveBug(String username, BugAddRequest request) {
        var bug = new Bugs();
        bug.setTitle(request.getTitle());
        bug.setDescription(request.getDescription());
        bug.setVersion(request.getVersion());
        bug.setFixedVersion(request.getFixedVersion());
        bug.setTargetDate(request.getTargetDate());
        bug.setStatus(BugStatus.Open);
        bug.setSeverity(request.getSeverity());

        var createdBy = userRepository.findByUsername(username).orElseThrow();
        var assignedTo = userRepository.findByUsername(request.getAssignedToUsername()).orElseThrow();

        bug.setUserCreated(createdBy);
        bug.setUserAssigned(assignedTo);

        bug = bugRepository.save(bug);
        return bug;
    }

    Attachments saveAttachment(long bugId, byte[] attCont) {
        var bug = bugRepository.findById(bugId).orElseThrow();

        var attachment = new Attachments();
        attachment.setAttContent(attCont);
        attachment.setBug(bug);

        attachment = attachmentRepository.save(attachment);
        return attachment;
    }

    void saveHistory(BugStatus beforeStatus, Bugs bug) {
        if (bug.getStatus() == beforeStatus)
            return;

        LocalDate currentDate = LocalDate.now();

        var historyEntry = new History();
        historyEntry.setModifiedDate(currentDate);
        historyEntry.setBeforeStatus(beforeStatus);
        historyEntry.setAfterStatus(bug.getStatus());
        historyEntry.setBug(bug);

        historyRepository.save(historyEntry);
    }

    Bugs overrideBug(long bugId, BugUpdateRequest request) {
        var assignee = request.getAssignedToUsername() == null ? null :
                userRepository.findByUsername(request.getAssignedToUsername()).orElseThrow();
        
        var bug = bugRepository.findById(bugId).orElseThrow();

        if (request.getTitle() != null)
            bug.setTitle(request.getTitle());
        if (request.getDescription() != null)
            bug.setDescription(request.getDescription());
        if (request.getVersion() != null)
            bug.setVersion(request.getVersion());
        if (request.getFixedVersion() != null)
            bug.setFixedVersion(request.getFixedVersion());
        if (request.getTargetDate() != null)
            bug.setTargetDate(request.getTargetDate());
        if (request.getSeverity() != null)
            bug.setSeverity(request.getSeverity());
        if (request.getStatus() != null)  {
            validateStatusChange(bug.getStatus(), request.getStatus());
            bug.setStatus(request.getStatus());
        }
        if (assignee != null)
            bug.setUserAssigned(assignee);

        bug = bugRepository.save(bug);
        return bug;
    }

    void validateStatusChange(BugStatus prevStatus, BugStatus newStatus) {
        if (prevStatus == newStatus || prevStatus == null)
            return;

        if (prevStatus == BugStatus.Open && (newStatus == BugStatus.In_Progress || newStatus == BugStatus.Rejected))
            return;

        if (prevStatus == BugStatus.In_Progress &&
                (newStatus == BugStatus.Info_Needed || newStatus == BugStatus.Fixed || newStatus == BugStatus.Rejected))
            return;

        if (prevStatus == BugStatus.Info_Needed && newStatus == BugStatus.In_Progress)
            return;

        if (prevStatus == BugStatus.Fixed && (newStatus == BugStatus.Closed || newStatus == BugStatus.Open))
            return;

        if (prevStatus == BugStatus.Rejected && newStatus == BugStatus.Closed)
            return;

        throw new RuntimeException("Invalid status change.");
    }

}
