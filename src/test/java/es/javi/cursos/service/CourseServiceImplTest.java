package es.javi.cursos.service;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.repository.CourseRepository;
import es.javi.cursos.repository.SubjectRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class CourseServiceImplTest {

    CourseRepository courseRepository = mock(CourseRepository.class);
    SubjectRepository subjectRepository = mock(SubjectRepository.class);
    CourseService sut = new CourseServiceImpl(courseRepository, subjectRepository);

    @Test
    public void find_all_courses(){
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(courseRepository).findAllByActiveTrue(pageable);

        // Act
        Page<Course> courses = sut.findAllByActiveTrue(pageable);

        // Assert
        verify(courseRepository).findAllByActiveTrue(pageable);
        assertThat(courses, equalTo(page));
    }

    @Test
    public void add_subject_to_course(){
        // Arrange
        Teacher teacher = new Teacher.Builder().id(1L).name("Juan").build();
        Course course = new Course.Builder().id(1L).name("Programacion").level("basico").teacher(teacher).active(true).build();
        Subject subject = new Subject.Builder().name("Programacion 3").course(course).build();
        doReturn(course).when(courseRepository).findById(1L);

        // Act
        sut.addSubject(1L, subject);

        // Assert
        verify(subjectRepository).save(subject);
    }

    @Test
    public void find_all_subjects_from_first_course() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 20);
        Page page = Page.empty();
        doReturn(page).when(subjectRepository).findAllByCourseId(1L, pageable);

        // Act
        Page<Subject> subjects = sut.findAllByCourseId(1L, pageable);

        // Assert
        verify(subjectRepository).findAllByCourseId(1L, pageable);
        assertThat(subjects, equalTo(page));
    }
}