package com.tykon.api.framework.service.upload.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tykon.api.framework.service.core.annotation.Timed;
import com.tykon.api.framework.service.core.exception.AppCommonException;
import com.tykon.api.framework.service.core.util.DateUtil;
import com.tykon.api.framework.service.core.util.ImageUtil;
import com.tykon.api.framework.service.core.util.S3Upload;

@Service
public class UploadMultipartService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @param file
	 * @param participantId
	 * @return always unique String while uploading images
	 */
	public String generateFileName(File file, String uploadPath) {
		String fileName = null;
		int randomPIN = (int) (Math.random() * 9000) + 1000;
		String imageExtension = ImageUtil.getFileExtension(file);
		String imageName = ImageUtil.getFileNameWithOutExtension(file.getName());
		fileName = uploadPath + imageName.toLowerCase().replaceAll(" ", "_") + "_" + DateUtil.getCurrentTimeStamp()
				+ randomPIN + "." + imageExtension;
		return fileName;
	}

	/**
	 * Creates and aggregates task to upload the images to AWS S3
	 * 
	 * @param eventImageSubFolder
	 * 
	 * @param mapOfUploadedFileNameAndDimension
	 * 
	 * @param imageFiles
	 * @return
	 * @throws AppCommonException
	 */
	public List<String> uploadToS3(List<File> listOfFiles, String imageUploadedPath) throws AppCommonException {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<FutureTask<String>> taskList = new ArrayList<>();
		splitFileUploadsTask(executor, taskList, listOfFiles, imageUploadedPath);
		List<String> uploadedImageUrls = aggregateResponses(taskList);
		return uploadedImageUrls;
	}

	/**
	 * 
	 * @param executor
	 * @param taskList
	 * @param imageUploadedPath
	 * @param imagesFiles
	 * @throws SheCommonException
	 */
	private void splitFileUploadsTask(ExecutorService executor, List<FutureTask<String>> taskList, List<File> files,
			String imageUploadedPath) throws AppCommonException {
		files.forEach(file -> {
			String fileName = generateFileName(file, imageUploadedPath);
			String uplaodedFileImageAttribute = ImageUtil.getFileExtension(file);
			addTasks(executor, taskList, file, fileName, uplaodedFileImageAttribute);
		});
	}

	/**
	 * Call ImageFileUploadCallable method to upload images AWS3 server
	 * 
	 * @param executor
	 * @param taskList
	 * @param file
	 * @param uniqueKey
	 * @param uplaodedFileImageAttribute
	 */
	private void addTasks(ExecutorService executor, List<FutureTask<String>> taskList, File file, String fileName,
			String uplaodedFileImageAttribute) {
		FutureTask<String> futureTask = new FutureTask<String>(
				new FileUploadCallable(file, fileName, uplaodedFileImageAttribute));
		taskList.add(futureTask);
		executor.execute(futureTask);
	}

	/**
	 * aggregateResponses get all uploaded images with task list
	 * 
	 * @param taskList
	 * @return
	 * @throws SheCommonException
	 */
	private List<String> aggregateResponses(List<FutureTask<String>> taskList) throws AppCommonException {
		List<String> uploadedImageUrls = new ArrayList<>();
		for (FutureTask<String> task : taskList) {
			try {
				String uploadedImageUrl = task.get();
				if (uploadedImageUrl != null) {
					logger.info("Uploaded new image ulr is =" + uploadedImageUrl);
					uploadedImageUrls.add(uploadedImageUrl);
				} else {
					logger.error("Error while uploading images in threads, null response from image upload task.");
					throw new AppCommonException("Error while uploading images, got null image url.");
				}
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error in executing image upload task threads.", e);
				throw new AppCommonException("Error in executing image upload task threads");
			}
		}
		return uploadedImageUrls;
	}

	/**
	 * Callable implementation for creating tasks to upload images to the AWS S3
	 * bucket
	 */
	@Timed
	private class FileUploadCallable implements Callable<String> {

		private File imageFile;

		private String fileName;

		private String uplaodedFileImageAttribute;

		public FileUploadCallable(File imageFile, String fileName, String uplaodedFileImageAttribute) {
			this.imageFile = imageFile;
			this.fileName = fileName;
			this.uplaodedFileImageAttribute = uplaodedFileImageAttribute;
		}

		@Override
		public String call() throws AppCommonException, FileNotFoundException {
			InputStream targetStream = new FileInputStream(imageFile);
			S3Upload.uploadInS3Bucket(null, fileName, targetStream, "image/" + uplaodedFileImageAttribute);
			return fileName;
		}
	}

	/**
	 * Copies bytes from an InputStream source to a file destination
	 * 
	 * @param uploadedFileList
	 * @return
	 * @throws AppCommonException
	 */
	public List<File> copyBytesFromInputStreamToFile(MultipartFile[] files) throws AppCommonException {
		List<File> uploadedFileList = new ArrayList<>();
		for (MultipartFile file : files) {
			File imageFileToUpload = new File("/tmp/" + file.getOriginalFilename());
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), imageFileToUpload);
			} catch (IOException e) {
				logger.error(
						"error in uploading vedio or other file to server. Either the file is corrupt or not able to upload ",
						e);
				throw new AppCommonException(
						"error in uploading vedio or other file to server. Either the file is corrupt or not able to upload");
			}
			uploadedFileList.add(imageFileToUpload);
		}
		return uploadedFileList;
	}
}
