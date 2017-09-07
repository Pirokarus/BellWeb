package project.server.hibernate.services;

import project.server.hibernate.entities.ReferencesTableEntity;

import java.util.List;

public interface ReferencesTableService {

    void save(ReferencesTableEntity entity);

    List<ReferencesTableEntity> findAllByGroupId(int groupId);

    List<ReferencesTableEntity> findAllByContactId(int contactId);

    void delete(int contactId, int groupId);
}
