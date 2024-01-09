package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, Long>
{
}
