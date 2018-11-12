package es.javi.cursos.repository;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import es.javi.cursos.model.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SubjectRepositoryIT {
    @Autowired SubjectRepository sut;

    @Test
    public void find_subjects_should_returns_and_contains_programacion(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Long id = 1L;

        // Act
        Page<Subject> courses = sut.findAllByCourseId(id, pageable);

        // Assert
        assertThat(courses.getTotalElements(), equalTo(3L));
        assertTrue(courses.getContent().stream().anyMatch(c -> c.getCourse().getName().equals("Programacion")));
    }

    @Test
    public void add_subject_to_course_and_find_it(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Teacher teacher = new Teacher.Builder().id(1L).name("Juan").build();
        Course course = new Course.Builder().id(1L).name("Programacion").teacher(teacher).level("basico").active(true).build();
        Subject subject = new Subject.Builder().name("Programacion 3").course(course).build();

        // Act
        sut.save(subject);

        // Assert
        Page<Subject> subjects = sut.findAllByCourseId(course.getId(), pageable);
        assertTrue(subjects.getContent().stream().anyMatch(s -> s.getName().equals("Programacion 3")));
    }
}
