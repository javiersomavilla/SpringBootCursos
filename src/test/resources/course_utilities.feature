Feature: course utilities

Scenario: client makes call to GET /courses
    When the course client calls courses endpoint /courses by page of 0 and size of 10
    Then the course client receives status code of 200
    And the course client receives "Programacion", "Matematicas", "Fisica", "EDA" and "IS" courses
    And the course client receives pagination

Scenario: client makes call to GET /courses/{courseId}/subjects
    When the course client calls courses/subjects endpoint /courses/1/subjects by page of 0 and size of 10
    Then the course client receives status code of 200
    And the course client receives "Programacion 1", and "Programacion 2" subjects
    And the course client receives pagination

Scenario: client makes call to POST /courses/{courseId}/subjects
    When the course client calls courses/subjects endpoint /courses/1/subjects with "Nueva asignatura"
    Then the course client receives status code of 201
    And the course client receives "Nueva asignatura" subject