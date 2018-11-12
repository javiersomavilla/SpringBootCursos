package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.repository.CourseRepository;
import es.javi.cursos.repository.TeacherRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TeacherServiceImplTest {

    TeacherRepository teacherRepository = mock(TeacherRepository.class);
    CourseRepository courseRepository = mock(CourseRepository.class);
    TeacherService sut = new TeacherServiceImpl(teacherRepository, courseRepository);

    @Test
    public void find_all_teachers() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(teacherRepository).findAll(pageable);

        // Act
        Page<Teacher> teachers = sut.findAll(pageable);

        // Assert
        verify(teacherRepository).findAll(pageable);
        assertThat(teachers, equalTo(page));
    }

    @Test
    public void add_teacher(){
        // Arrange
        Teacher saveTestName = new Teacher.Builder().name("saveTestName").build();

        // Act
        sut.save(saveTestName);

        // Assert
        verify(teacherRepository).save(saveTestName);
    }



    @Test
    public void add_course(){
        // Arrange
        Course course = new Course.Builder().active(true).name("Programacion 2").level("intermedio").build();
        Teacher teacher = new Teacher.Builder().name("Juan").id(1L).build();
        doReturn(teacher).when(teacherRepository).findById(5L);
        Course courseToSave = new Course.Builder().active(true).name("Programacion 2").teacher(teacher).level("intermedio").build();

        // Act
        sut.addCourse(5L, course);

        // Assert
        verify(courseRepository).save(courseToSave);
    }
}