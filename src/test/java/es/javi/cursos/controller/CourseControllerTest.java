package es.javi.cursos.controller;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.service.CourseService;
import es.javi.cursos.service.CourseServiceImpl;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class CourseControllerTest {

    CourseService courseService = mock(CourseServiceImpl.class);
    CourseController sut = new CourseController(courseService);

    @Test
    public void find_all_courses(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(courseService).findAllByActiveTrue(pageable);

        // Act
        List<Course> courses = sut.getCourses(0, 20).getBody().getContent();

        // Assert
        verify(courseService).findAllByActiveTrue(pageable);
        assertThat(courses.size(), equalTo((int) page.getTotalElements()));
    }

    @Test
    public void add_subject(){
        // Arrange
        Teacher teacher = new Teacher.Builder().id(1L).name("Juan").build();
        Course course = new Course.Builder().id(1L).name("Programacion").level("basico").teacher(teacher).active(true).build();
        Subject subject = new Subject.Builder().name("Programacion 3").course(course).build();

        // Act
        sut.addSubject(1L, subject);

        // Assert
        verify(courseService).addSubject(1L, subject);
    }

    @Test
    public void find_all_subjects_from_first_course(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(courseService).findAllByCourseId(1L, pageable);

        // Act
        List<Subject> subjects = sut.getSubjects(1L,0,20).getBody().getContent();

        // Assert
        verify(courseService).findAllByCourseId(1L,pageable);
        assertThat(subjects.size(), equalTo((int)page.getTotalElements()));
    }
}