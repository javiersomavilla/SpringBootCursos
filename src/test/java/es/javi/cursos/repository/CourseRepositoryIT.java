package es.javi.cursos.repository;

import es.javi.cursos.model.Course;
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
public class CourseRepositoryIT {
    @Autowired CourseRepository sut;

    @Test
    public void find_active_courses_should_return_themn_and_contains_juan_as_teacher(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Teacher juan = new Teacher.Builder().id(1L).name("Juan").build();

        // Act
        Page<Course> courses = sut.findAllByActiveTrue(pageable);

        // Assert
        assertThat(courses.getTotalElements(), equalTo(7L));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.getTeacher().equals(juan)));
    }

     @Test
    public void add_course_and_find_it(){
        // Arrange
        Teacher teacher = new Teacher.Builder().name("Juan").id(1L).build();
        Course saveTestCourse = new Course.Builder().active(true).name("Programacion").teacher(teacher).level("basico").build();

        // Act
        sut.save(saveTestCourse);

        // Assert
        Page<Course> courses = sut.findAllByActiveTrue(PageRequest.of(0, 20));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.getTeacher().equals(teacher)));
    }
}
