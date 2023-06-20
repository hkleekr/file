package file.fileupload.repository;

import file.fileupload.domain.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * db에 저장되지 않고 메모리에 저장되며, 서버 중지 시 휘발됨
 */
@Repository
public class MemoryFileRepository {

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

    public List<FileInfo> findAll() {
        return new ArrayList<>(store.values());
    }

}
