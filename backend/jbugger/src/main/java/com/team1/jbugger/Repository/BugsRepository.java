package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.Bugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugsRepository extends JpaRepository<Bugs, Long> {
}
