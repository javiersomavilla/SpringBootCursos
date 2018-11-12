package es.javi.cursos.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_generator", sequenceName = "teacher_sequence", initialValue = 100)
    @GeneratedValue(generator = "teacher_generator")
    private Long id;

    @Column
    private String name;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return new EqualsBuilder()
                .append(id, teacher.id)
                .append(name, teacher.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }

    public static class Builder {
        Teacher object;
        public Builder(){
            object = new Teacher();
        }

        public Builder(Teacher teacher){
            this();
            object.setId(teacher.getId());
            object.setName(teacher.getName());
        }

        public Builder id(Long id){
            object.setId(id);
            return this;
        }

        public Builder name(String name){
            object.setName(name);
            return this;
        }

        public Teacher build() {
            return object;
        }
    }
}
