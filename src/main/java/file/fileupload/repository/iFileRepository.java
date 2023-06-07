package file.fileupload.repository;

import file.fileupload.domain.FileInfo;

import java.util.List;
import java.util.Optional;


public interface iFileRepository {

    FileInfo save(FileInfo fileInfo);
    Optional<FileInfo> findById(Long id); // Optional 때문에 extend 못하고 있음
    List<FileInfo> findAll();
}

