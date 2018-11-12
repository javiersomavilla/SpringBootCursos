package es.javi.cursos.controller;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Teacher;
import es.javi.cursos.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {
    @Autowired private TeacherService teacherService;

    TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<Page<Teacher>> getTeachers(@RequestParam int page, @RequestParam int size){
        Page<Teacher> teachers = teacherService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<Page<Teacher>> (teachers, HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @PostMapping("/teachers/{id}/courses")
    public ResponseEntity<Course> addCourse(@PathVariable Long id, @RequestBody Course course){
        teacherService.addCourse(id, course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

}
