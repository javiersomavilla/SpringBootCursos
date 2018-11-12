package es.javi.cursos.repository;

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
public class TeacherRepositoryIT {

    @Autowired  TeacherRepository sut;

    @Test
    public void find_all_teachers_should_return_them_and_contains_juan(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Teacher juan = new Teacher.Builder().id(1L).name("Juan").build();

        // Act
        Page<Teacher> teachers = sut.findAll(pageable);

        // Assert
        assertThat(teachers.getTotalElements(), equalTo(6L));
        assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.equals(juan)));
    }

    @Test
    public void add_teacher_and_find_it(){
        // Arrange
        Teacher saveTestName = new Teacher.Builder().name("saveTestName").build();

        // Act
        sut.save(saveTestName);

        // Assert
        Page<Teacher> teachers = sut.findAll(PageRequest.of(0, 20));
        assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.getName().equals("saveTestName")));
    }
}
