package es.javi.cursos.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class Course implements Cloneable{
    @Id
    @SequenceGenerator(name = "course_generator", sequenceName = "course_generator", initialValue = 100)
    @GeneratedValue(generator = "course_generator")
    private Long id;

    @Column
    private String name;

    @Column
    private String level;

    @Column
    private boolean active;

    @ManyToOne
    private Teacher teacher;


    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    private void setLevel(String level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return new EqualsBuilder()
                .append(active, course.active)
                .append(id, course.id)
                .append(name, course.name)
                .append(level, course.level)
                .append(teacher, course.teacher)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(level)
                .append(active)
                .append(teacher)
                .toHashCode();
    }


    public static class Builder {
        Course object;

        public Builder(){
            object = new Course();
        }

        public Builder(Course course) {
            this();
            object.setId(course.getId());
            object.setName(course.getName());
            object.setLevel(course.getLevel());
            object.setActive(course.isActive());
            object.setTeacher(course.getTeacher());
        }

        public Builder id(Long id){
            object.setId(id);
            return this;
        }

        public Builder name(String title){
            object.setName(title);
            return this;
        }

        public Builder level(String level){
            object.setLevel(level);
            return this;
        }

        public Builder active(boolean active){
            object.setActive(active);
            return this;
        }

        public Builder teacher(Teacher teacher){
            object.setTeacher(teacher);
            return this;
        }

        public Course build() {
            return object;
        }
    }
}
