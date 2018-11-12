package es.javi.cursos.controller;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.service.TeacherService;
import es.javi.cursos.service.TeacherServiceImpl;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TeacherControllerTest {

    TeacherService teacherService = mock(TeacherServiceImpl.class);
    TeacherController sut = new TeacherController(teacherService);

    @Test
    public void find_all_teachers(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(teacherService).findAll(pageable);

        // Act
        List<Teacher> teachers = sut.getTeachers(0,20).getBody().getContent();

        // Assert
        verify(teacherService).findAll(pageable);
        assertThat(teachers.size(), equalTo((int)page.getTotalElements()));
    }

    @Test
    public void add_teacher(){
        // Arrange
        Teacher saveTestName = new Teacher.Builder().name("saveTestName").build();

        // Act
        sut.addTeacher(saveTestName);

        // Assert
        verify(teacherService).save(saveTestName);
    }


    @Test
    public void add_course(){
        // Arrange
        Teacher teacher = new Teacher.Builder().name("Juan").id(1L).build();
        Course saveTestCourse = new Course.Builder().active(true).name("Programacion").teacher(teacher).level("basico").build();

        // Act
        sut.addCourse(1L, saveTestCourse);

        // Assert
        verify(teacherService).addCourse(1L, saveTestCourse);
    }
}