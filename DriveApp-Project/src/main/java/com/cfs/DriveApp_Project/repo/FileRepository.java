package com.cfs.DriveApp_Project.repo;

import com.cfs.DriveApp_Project.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<FileEntity,Long> {

}
