Feature: course utilities

Scenario: client makes call to GET /teachers
    When the teacher client calls teachers endpoint /teachers by page of 0 and size of 10
    Then the teacher client receives status code of 200
    And the teacher client receives "Juan", "Javi", "Carlos", "Peter" and "Cristiano" teachers
    And the teacher client receives pagination

Scenario: client makes call to POST /teachers
    When the teacher client calls teachers endpoint /teachers with "Maria"
    Then the teacher client receives status code of 201
    And the teacher client receives "Maria" teacher

Scenario: client makes call to POST /teachers/{teacherId}/courses
    When the teacher client calls teachers/courses endpoint /teachers/1/courses with "Nueva Asignatura"
    Then the teacher client receives status code of 201
    And the teacher client receives "Nueva Asignatura" subject