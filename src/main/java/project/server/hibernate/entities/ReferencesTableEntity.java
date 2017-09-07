package project.server.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "references_table", schema = "public", catalog = "postgres")
public class ReferencesTableEntity {
    private int id;
    private ContactsEntity contactsByContactId;
    private GroupsEntity groupsByGroupId;

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

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    public ContactsEntity getContactsByContactId() {
        return contactsByContactId;
    }

    public void setContactsByContactId(ContactsEntity contactsByContactId) {
        this.contactsByContactId = contactsByContactId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public GroupsEntity getGroupsByGroupId() {
        return groupsByGroupId;
    }

    public void setGroupsByGroupId(GroupsEntity groupsByGroupId) {
        this.groupsByGroupId = groupsByGroupId;
    }

    @Override
    public String toString() {
        return "ReferencesTableEntity{" +
                "id=" + id +
                ", contactsByContactId=" + contactsByContactId +
                ", groupsByGroupId=" + groupsByGroupId +
                '}' + '\n';
    }
}
