package project.server.hibernate.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public", catalog = "postgres")
public class RolesEntity {

    private int id;
    private String name;
    private Set<UsersEntity> users;

    public RolesEntity() {
    }

    @ManyToMany(mappedBy = "roles")
    public Set<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersEntity> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (getId() != that.getId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RolesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
