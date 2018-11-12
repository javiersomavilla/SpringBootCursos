package es.javi.cursos.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @SequenceGenerator(name = "subject_generator", sequenceName = "subject_generator", initialValue = 100)
    @GeneratedValue(generator = "subject_generator")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private Course course;

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

    public Course getCourse() {
        return this.course;
    }

    private void setCourse(Course course){
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return new EqualsBuilder()
                .append(id, subject.id)
                .append(name, subject.name)
                .append(course, subject.course)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(course)
                .toHashCode();
    }


    public static class Builder {
        Subject object;

        public Builder() {
            object = new Subject();
        }

        public Builder(Subject subject){
            this();
            object.setId(subject.getId());
            object.setName(subject.getName());
            object.setCourse(subject.getCourse());
        }

        public Builder id(Long id) {
            object.setId(id);
            return this;
        }

        public Builder name(String name) {
            object.setName(name);
            return this;
        }

        public Builder course(Course course) {
            object.setCourse(course);
            return this;
        }

        public Subject build() {
            return object;
        }
    }
}
