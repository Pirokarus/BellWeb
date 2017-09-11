package project.server.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.hibernate.dao.ReferencesTableDAO;
import project.server.hibernate.entities.ReferencesTableEntity;

import java.util.List;

@Service("referencesTableService")
@Transactional
public class ReferencesTableServiceImpl implements ReferencesTableService {

    @Autowired
    private ReferencesTableDAO referencesTableDAO;

    @Override
    public void save(ReferencesTableEntity entity) {
        referencesTableDAO.save(entity);
    }

    @Override
    public List<ReferencesTableEntity> findAllByGroupId(int groupId) {
        return referencesTableDAO.findAllByGroupId(groupId);
    }

    @Override
    public List<ReferencesTableEntity> findAllByContactId(int contactId) {
        return referencesTableDAO.findAllByContactId(contactId);
    }

    @Override
    public void delete(int contactId, int groupId) {
        referencesTableDAO.delete(contactId, groupId);
    }
}
