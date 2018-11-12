package es.javi.cursos.repository;

import es.javi.cursos.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface TeacherRepository extends Repository<Teacher, Long> {

    Page<Teacher> findAll(Pageable pageable);
    Teacher findById(Long id);
    void save(Teacher teacher);
}
