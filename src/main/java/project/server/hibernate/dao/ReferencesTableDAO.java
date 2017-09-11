package project.server.hibernate.dao;

import project.server.hibernate.entities.ReferencesTableEntity;

import java.util.List;

public interface ReferencesTableDAO {

    void save(ReferencesTableEntity entity);

    List<ReferencesTableEntity> findAllByGroupId(int group_id);

    List<ReferencesTableEntity> findAllByContactId(int contact_id);

    void delete(int contactId, int groupId);

}
