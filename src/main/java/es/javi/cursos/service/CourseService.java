package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<Course> findAllByActiveTrue(Pageable pageable);
    void addSubject(Long id, Subject subject);
    Page<Subject> findAllByCourseId(Long id, Pageable pageable);
}
