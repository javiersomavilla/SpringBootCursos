package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<Teacher> findAll(Pageable pageable);
    void save(Teacher teacher);
    void addCourse(Long id, Course course);
}
