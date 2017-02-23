package com.hi.cord.first.controller.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.first.entity.file.FileData;
import com.hi.cord.first.service.file.FileDataService;

@RequestMapping(value = "/file")
public class FileDataController {

	@Autowired
	private FileDataService fileDataService;
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	static final Logger log = LoggerFactory.getLogger(FileDataController.class);

	private final String FILE_PATH = "/Users/hunseol/Desktop/file/";
	// private final String FILE_PATH = "/Users/HunSeol/Desktop/file/";

	/**
	 * 파일 저장
	 * 
	 * @param FileData,
	 *            MultiFile
	 * @return String - view(ajax)
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult fileSave(MultipartHttpServletRequest multipart, AjaxResult ajaxResult) throws IOException {
		// 파일 가져오기.
		// 1 MB = 1024 * 1024 Bytes || 1 GB = 1024 * 1024 * 1024 Bytes.
		List<MultipartFile> multiFiles = multipart.getFiles("boardWithFileList");
		log.info("param : " + multiFiles.toString());
		int sum = 0;

		//각각의 파일 크기 계산 
		for (int i = 0; i < multiFiles.size(); i++) {
			if (multiFiles.get(i).getSize() > 1024 * 1024 * 30) {
				ajaxResult.setResult("over");
				return ajaxResult;
			}
			sum += multiFiles.get(i).getSize();
		}
		// 파일 크기 총합 계산.
		if (sum > 1024 * 1024 * 30) {
			ajaxResult.setResult("over");
			return ajaxResult;
		}

		// 파일 저장
		List<FileData> fileDataList = new ArrayList<>();
		for (int i = 0; i < multiFiles.size(); i++) {
			FileData fileData = new FileData();

			//확장자명 추출, 2개시 에러처리.
			String orginalFileName = multiFiles.get(i).getOriginalFilename();
			String splitName[] = orginalFileName.split(".");
			if (splitName.length > 2) {
				ajaxResult.setResult("invalid");
				return ajaxResult;
			}
			
			//파일 OriginalName과 확장자명 저장..
			fileData.setFileDataOriginName(splitName[0]);
			fileData.setFileDataType(splitName[1]);

			String contentType = multiFiles.get(i).getContentType();
			log.info("TEST : " + contentType);
			// fileData.setFileDataType(contentType);

			//파일이름 Bcrypt로 암호화.
			String encryptFileName = bCrypt.encode(orginalFileName);
			String savedPath = FILE_PATH + encryptFileName;

			fileData.setFileDataSavedName(encryptFileName);
			fileData.setFileDataSavedPath(savedPath);

			// File Upload
			File uploadFile = new File(savedPath);
			try {
				multiFiles.get(i).transferTo(uploadFile);
			} catch (IllegalStateException e) {
				log.debug("File Catch IllegalStateException");
				e.printStackTrace();
			} catch (IOException e) {
				log.debug("File Catch IOException");
				e.printStackTrace();
			}

			// 이상 없을시 파일리스트에 담는다.
			fileDataList.add(fileData);
		}
		// FileDB저장
		for (int i = 0; i < fileDataList.size(); i++) {
			fileDataService.save(fileDataList.get(i));
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}
