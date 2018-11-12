package es.javi.cursos.repository;

import es.javi.cursos.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CourseRepository extends Repository<Course, Long> {

    Page<Course> findAllByActiveTrue(Pageable pageable);
    Course findById(Long id);
    void save(Course course);
}
