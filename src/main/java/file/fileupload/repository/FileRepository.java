package file.fileupload.repository;

import file.fileupload.domain.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class FileRepository {

    private final Map<Long, FileInfo> store = new HashMap<>();
    private long sequence = 0L;

    public FileInfo save(FileInfo fileInfo) {
        fileInfo.setId(++sequence);
        store.put(fileInfo.getId(), fileInfo);
        return fileInfo;
    }

    public FileInfo findById(Long id) {
        return store.get(id);
    }


//    public final EntityManager em;
//
//    public FileRepository(EntityManager em) {
//        this.em = em;
//    }

//    @Override
//    public FileInfo save(FileInfo fileInfo) {
//        em.persist(fileInfo);
//        return fileInfo;
//    }
//
//    @Override
//    public Optional<FileInfo> findById(Long id) {
//        FileInfo fileInfo = em.find(FileInfo.class, id);
//        return Optional.ofNullable(fileInfo);
//    }
//
//    @Override
//    public List<FileInfo> findAll() {
//        return em.createQuery("select m from FileInfo m", FileInfo.class)
//                .getResultList();
//    }
}
