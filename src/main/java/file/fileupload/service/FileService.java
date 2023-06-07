package file.fileupload.service;

import file.fileupload.domain.FileInfo;
import file.fileupload.domain.UploadFile;
import file.fileupload.repository.MemoryFileRepository;

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

    private final MemoryFileRepository fileRepository;

    @Value("${file.dir}")
    private String fileDir;

    /**
     * 생성자
     */
    public FileService(MemoryFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * 파일 저장 경로(without 확장자)
     */
    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    /**
     * 파일 여러 개 저장
     */
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    /**
     * 파일 한 개 저장
     */
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName);
    }

    /**
     * 파일 저장 명 생성: UUID
     */
    private String createStoreFileName(String originalFileName) {
//        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid;
//        return uuid + '.' + ext;
    }

    /**
     * 확장자 추출
     */
//    private String extractExt(String originalFileName) {
//        int position = originalFileName.lastIndexOf(".");
//        return originalFileName.substring(position + 1);
//    }

    /**
     * 전체 파일 조회
     */
    public List<FileInfo> findFilesAll() {
        return fileRepository.findAll();
    }

    /**
     * id로 파일 조회
     */
    public FileInfo findFile(Long id) {
        return fileRepository.findById(id);
    }
}
