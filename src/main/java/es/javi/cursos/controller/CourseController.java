package es.javi.cursos.controller;

import es.javi.cursos.model.Course;
import es.javi.cursos.model.Subject;
import es.javi.cursos.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired private CourseService courseService;

    CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Page<Course>> getCourses(@RequestParam int page, @RequestParam int size){
        Page<Course> courses = courseService.findAllByActiveTrue(PageRequest.of(page, size));
        return new ResponseEntity<Page<Course>> (courses, HttpStatus.OK);
    }

    @PostMapping("/courses/{id}/subjects")
    public ResponseEntity<Subject> addSubject(@PathVariable Long id, @RequestBody Subject subject){
        courseService.addSubject(id, subject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @GetMapping("/courses/{id}/subjects")
    public ResponseEntity<Page<Subject>> getSubjects(@PathVariable Long id,@RequestParam int page, @RequestParam int size){
        Page<Subject> subjects = courseService.findAllByCourseId(id, PageRequest.of(page, size));
        return new ResponseEntity<Page<Subject>> (subjects, HttpStatus.OK);
    }

}
