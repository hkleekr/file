package file.fileupload.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 파일 도메인
 */
@Data
//@Entity
public class FileInfo {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private UploadFile attachedFile;             // 1개
    private List<UploadFile> attachedFiles;      // 여러 개
//    private Long size;
//    private Timestamp createdDate;
//    private Timestamp deletedDate;
//    private Boolean deletedYn;
}
