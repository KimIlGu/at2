package com.sbs.kig.at.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sbs.kig.at.dto.File;
import com.sbs.kig.at.dto.ResultData;
import com.sbs.kig.at.service.FileService;
import com.sbs.kig.at.service.VideoStreamService;
import com.sbs.kig.at.util.Util;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private VideoStreamService videoStreamService;
	
	// 구아바의 기능 : 스프링에서 DB에 있는 파일을 10분 동안 가지고 있겠다.
	private LoadingCache<Integer, File> fileCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(2, TimeUnit.MINUTES).build(new CacheLoader<Integer, File>() {
		@Override
		public File load(Integer fileId) {
			return fileService.getFileById(fileId);
		}
	});

	@RequestMapping("/usr/file/streamVideo")
	public ResponseEntity<byte[]> streamVideo(@RequestHeader(value = "Range", required = false) String httpRangeList,
			int id) {
		// 캐시 안의 데이터를 가져오는 방법, 10분 동안 DB를 귀찮게 하지 않음.
		File file = Util.getCacheData(fileCache, id);
		
		// file.getBody() : 동영상의 바이트 배열, 파일의 크기, 파일의 확장자 
		// httpRangeList : 브라우저가 동영상을 요청할 때 어디부터 어디까지 달라고 하는데, 파라미터가 아닌 헤더로 정보를 넘겨줌.
		return videoStreamService.prepareContent(new ByteArrayInputStream(file.getBody()), file.getFileSize(),
				file.getFileExt(), httpRangeList);
	}

	// 다중 파일 업로드
	@RequestMapping("/usr/file/doUploadAjax")
	@ResponseBody
	public ResultData uploadAjax(@RequestParam Map<String, Object> param, HttpServletRequest req,
			MultipartRequest multipartRequest) {
		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		
		List<Integer> fileIds = new ArrayList<>();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);

			String[] fileInputNameBits = fileInputName.split("__");

			if (fileInputNameBits[0].equals("file")) {
				byte[] fileBytes = Util.getFileBytesFromMultipartFile(multipartFile);

				if (fileBytes == null || fileBytes.length == 0) {
					continue;
				}

				String relTypeCode = fileInputNameBits[1];
				int relId = Integer.parseInt(fileInputNameBits[2]);
				String typeCode = fileInputNameBits[3];
				String type2Code = fileInputNameBits[4];
				int fileNo = Integer.parseInt(fileInputNameBits[5]);
				String originFileName = multipartFile.getOriginalFilename();
				String fileExtTypeCode = Util.getFileExtTypeCodeFromFileName(multipartFile.getOriginalFilename());
				String fileExtType2Code = Util.getFileExtType2CodeFromFileName(multipartFile.getOriginalFilename());
				String fileExt = Util.getFileExtFromFileName(multipartFile.getOriginalFilename()).toLowerCase();
				int fileSize = (int) multipartFile.getSize();

				int fileId = fileService.saveFile(relTypeCode, relId, typeCode, type2Code, fileNo, originFileName,
						fileExtTypeCode, fileExtType2Code, fileExt, fileBytes, fileSize);

				fileIds.add(fileId);
			}
		}

		Map<String, Object> rsDataBody = new HashMap<>();
		rsDataBody.put("fileIdsStr", Joiner.on(",").join(fileIds));
		rsDataBody.put("fileIds", fileIds);

		return new ResultData("S-1", String.format("%d개의 파일을 저장했습니다.", fileIds.size()), rsDataBody);
	}
}