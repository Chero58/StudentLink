package com.wiseowls.StudentLink.Repositories;

import com.wiseowls.StudentLink.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    // Custom query to delete a job by ID
    void deleteById(@SuppressWarnings("null") Integer id);

    // Custom query to filter jobs
    @Query("SELECT j FROM Job j WHERE " +
           "(:company IS NULL OR j.company LIKE %:company%) AND " +
           "(:location IS NULL OR j.location LIKE %:location%) AND " +
           "(:skillsRequired IS NULL OR j.skillsRequired LIKE %:skillsRequired%) AND " +
           "(:duration IS NULL OR j.duration LIKE %:duration%) AND " +
           "(:openingsAvailable IS NULL OR j.openingsAvailable >= :openingsAvailable)")
    List<Job> findFilteredJobs(
            @Param("company") String company,
            @Param("location") String location,
            @Param("skillsRequired") String skillsRequired,
            @Param("duration") String duration,
            @Param("openingsAvailable") Integer openingsAvailable
    );


}