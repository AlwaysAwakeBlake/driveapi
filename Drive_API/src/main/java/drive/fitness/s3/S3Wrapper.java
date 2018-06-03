package drive.fitness.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.Base64;
import com.amazonaws.util.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

@Service
public class S3Wrapper {

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${jsa.s3.bucket}")
	private String bucket;
	
	public String uploadImage(String base64Image, String uploadKey) throws IOException {
		File compressed = compressImage(base64Image);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream fis = new FileInputStream(compressed);
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, fis, new ObjectMetadata());
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(compressed.length());
		metadata.setContentType("image/jpg");
		metadata.setCacheControl("public, max-age=31536000");

		amazonS3Client.putObject(bucket, uploadKey, fis, metadata);
		amazonS3Client.setObjectAcl(bucket, uploadKey, CannedAccessControlList.PublicRead);
		return "SUCCESS";
	}
	
	public File compressImage(String base64Image) throws IOException {
		String imageString = base64Image.split(",")[1];
		BufferedImage image = null;
		File compressedImageFile = new File("compress.jpg");
        OutputStream os =new FileOutputStream(compressedImageFile);
		byte[] imageByte = Base64.decode(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();
		image.getScaledInstance(96, 96, Image.SCALE_DEFAULT);
		
		Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();
        
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()){
	        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	        param.setCompressionQuality(0.2f);
        }
        writer.write(null, new IIOImage(image, null, null), param);
        
        ios.close();
        writer.dispose();
        return compressedImageFile;
	}


	private PutObjectResult upload(InputStream inputStream, String uploadKey) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());

		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

		IOUtils.closeQuietly(inputStream, null);

		return putObjectResult;
	}

	public List<PutObjectResult> upload(MultipartFile[] multipartFiles) {
		List<PutObjectResult> putObjectResults = new ArrayList<>();

		Arrays.stream(multipartFiles)
				.filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
				.forEach(multipartFile -> {
					try {
						putObjectResults.add(upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

		return putObjectResults;
	}

	public ResponseEntity<String> download(String key) throws IOException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

		S3Object s3Object = amazonS3Client.getObject(getObjectRequest);
		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);
		objectInputStream.close();
		String base64Image = Base64.encodeAsString(bytes);

		String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(base64Image.length());
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(base64Image, httpHeaders, HttpStatus.OK);
	}

	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

		return s3ObjectSummaries;
	}
}
