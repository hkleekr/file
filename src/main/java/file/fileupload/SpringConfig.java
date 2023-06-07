//package file.fileupload;
//
//import file.fileupload.repository.MemoryFileRepository;
//import file.fileupload.service.FileService;
//import org.springframework.context.annotation.Bean;
//
//public class SpringConfig {
//    private final MemoryFileRepository fileRepository;
//
//    public SpringConfig(MemoryFileRepository fileRepository) {
//        this.fileRepository = fileRepository;
//    }
//
//    @Bean
//    public FileService fileService() {
//        return new FileService(fileRepository);
//    }
//
//}
