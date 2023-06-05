package file.fileupload.controller;


import file.fileupload.domain.FileForm;
import file.fileupload.domain.FileInfo;
import file.fileupload.domain.UploadFile;
import file.fileupload.repository.FileRepository;
import file.fileupload.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileRepository fileRepository;
    private final FileService fileService;

    @GetMapping("/upload")
    public String newFile(@ModelAttribute FileForm form) {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@ModelAttribute FileForm form, RedirectAttributes redirectAttributes) throws IOException {

        UploadFile storeFile = fileService.storeFile(form.getAttachedFile());
        List<UploadFile> storeFiles = fileService.storeFiles(form.getAttachedFiles());

        // 데이터베이스에 저장
        FileInfo file = new FileInfo();
        file.setFileName(form.getFileName());
        file.setAttachedFile(storeFile);
        file.setAttachedFiles(storeFiles);
        fileRepository.save(file);

        redirectAttributes.addAttribute("fileId", file.getId());

        return "redirect:/files/{fileId}";
    }

    @GetMapping("/files/{id}")
    public String files(@PathVariable Long id, Model model) {
        FileInfo file = fileRepository.findById(id);
        model.addAttribute("file", file);
        return "file-view";
    }

    // 파일 다운로드
    @ResponseBody
    @GetMapping("attach/{fileId}")
    public ResponseEntity<Resource> downloadAttached(@PathVariable Long fileId) throws MalformedURLException {
        FileInfo file = fileRepository.findById(fileId);
        String storeFileName = file.getAttachedFile().getStoreFileName();
        String uploadFileName = file.getAttachedFile().getUploadFileName();

        UrlResource resource = new UrlResource("file: " + fileService.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
