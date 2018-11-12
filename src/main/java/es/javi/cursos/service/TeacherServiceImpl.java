package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.repository.CourseRepository;
import es.javi.cursos.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired private final TeacherRepository teacherRepository;
    @Autowired private final CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository){
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void addCourse(Long id, Course course) {
        Teacher teacher = teacherRepository.findById(id);
        Course courseWithTeacher = new Course.Builder(course).teacher(teacher).build();
        courseRepository.save(courseWithTeacher);
    }
}
