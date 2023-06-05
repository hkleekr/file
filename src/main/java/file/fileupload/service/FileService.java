package file.fileupload.service;

import file.fileupload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 파일 저장과 관련된 업무 처리
 * 확장자 보이지 않게 UUID로만 저장함
 */
@Service
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
//        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid;
//        return uuid + '.' + ext;
    }

    // 확장자 추출
//    private String extractExt(String originalFileName) {
//        int position = originalFileName.lastIndexOf(".");
//        return originalFileName.substring(position + 1);
//    }

}
