package drive.fitness.services.impl;

import com.amazonaws.services.s3.model.S3Object;

public interface S3Services {
	public S3Object downloadFile(String keyName);
	public void uploadFile(String keyName, String uploadFilePath);
}