package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import es.javi.cursos.repository.CourseRepository;
import es.javi.cursos.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired private final CourseRepository courseRepository;
    @Autowired private final SubjectRepository subjectRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SubjectRepository subjectRepository){
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Page<Course> findAllByActiveTrue(Pageable pageable) {
        return courseRepository.findAllByActiveTrue(pageable);
    }

    @Override
    @Transactional
    public void addSubject(Long id, Subject subject) {
        Course course = courseRepository.findById(id);
        Subject newSubject = new Subject.Builder(subject).course(course).build();
        subjectRepository.save(newSubject);
    }

    @Override
    public Page<Subject> findAllByCourseId(Long id, Pageable pageable) {
        return subjectRepository.findAllByCourseId(id, pageable);
    }

}
