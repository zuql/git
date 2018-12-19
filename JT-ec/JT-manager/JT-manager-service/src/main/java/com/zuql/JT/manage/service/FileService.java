package com.zuql.jt.manage.service;

import org.springframework.web.multipart.MultipartFile;

import com.zuql.jt.common.vo.PicUploadResult;

public interface FileService {

	PicUploadResult uploadFile(MultipartFile uploadFile);

}
