//package file.fileupload;
//
//import file.fileupload.repository.FileRepository;
//import file.fileupload.service.FileService;
//import jakarta.persistence.EntityManager;
//import org.springframework.context.annotation.Bean;
//
//import javax.sql.DataSource;
//
//public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean
//    public FileService fileService() {
//        return new FileService(fileRepository());
//    }
//
//    @Bean
//    public FileRepository fileRepository(){
//        return new FileRepository(em);
//    }
//}
