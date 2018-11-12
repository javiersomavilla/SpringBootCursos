package es.javi.cursos.repository;

import es.javi.cursos.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface SubjectRepository extends Repository<Subject, Long> {

    Page<Subject> findAllByCourseId(Long id, Pageable pageable);
    void save(Subject subject);
}
