insert into teacher(id, name) values (1, 'Juan')
insert into teacher(id, name) values (2, 'Javi')
insert into teacher(id, name) values (3, 'Carlos')
insert into teacher(id, name) values (4, 'Peter')
insert into teacher(id, name) values (5, 'Cristiano')


insert into course(id, name, level, active, teacher_id) values(1, 'Programacion', 'basico',true, 1)
insert into course(id, name, level, active, teacher_id) values(2, 'Matematicas', 'intermedio',true, 1)
insert into course(id, name, level, active, teacher_id) values(3, 'Fisica', 'basico',true, 2)
insert into course(id, name, level, active, teacher_id) values(4, 'EDA', 'avanzado',true, 2)
insert into course(id, name, level, active, teacher_id) values(5, 'IS', 'basico',true, 3)


insert into subject(id, name, course_id) values(1,'Programacion 1', 1)
insert into subject(id, name, course_id) values(2,'Programacion 2', 1)
insert into subject(id, name, course_id) values(3,'IS 1', 5)
insert into subject(id, name, course_id) values(4,'IS 2', 5)