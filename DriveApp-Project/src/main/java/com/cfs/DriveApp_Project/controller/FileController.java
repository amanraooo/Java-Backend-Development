package com.cfs.DriveApp_Project.controller;

import com.cfs.DriveApp_Project.service.FileServiceStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {

	private final FileServiceStorage fileServiceStorage;

	public FileController(FileServiceStorage fileServiceStorage) {
		this.fileServiceStorage = fileServiceStorage;
	}

	public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file,
											 @RequestParam(value = "parentFolderId", required = false) Long parentFolderId){
		try{
			String response = fileServiceStorage.saveFile(file,parentFolderId);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("File upload failed");
		}
	}
}
