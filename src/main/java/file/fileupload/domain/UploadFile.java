package file.fileupload.domain;

import lombok.Data;

/**
 * 업로드 정보 보관
 */
@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;  // 업로드 한 파일명
        this.storeFileName = storeFileName;    // 관리용 파일 명
    }
}

