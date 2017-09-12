package project.server.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "references_table", schema = "public", catalog = "postgres")
public class ReferencesTableEntity {
    private int id;
    private ContactsEntity contact_id;
    private GroupsEntity group_id;

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    public ContactsEntity getContactsEntity() {
        return contact_id;
    }

    public void setContactsEntity(ContactsEntity contact_id) {
        this.contact_id = contact_id;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public GroupsEntity getGroupsEntity() {
        return group_id;
    }

    public void setGroupsEntity(GroupsEntity group_id) {
        this.group_id = group_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferencesTableEntity that = (ReferencesTableEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
