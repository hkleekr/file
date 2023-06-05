package file.fileupload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 파일 저장용 폼
 */
@Data
public class FileForm {
    private Long id;
    private String fileName;
    private MultipartFile attachedFile;
    private List<MultipartFile> attachedFiles;
}
