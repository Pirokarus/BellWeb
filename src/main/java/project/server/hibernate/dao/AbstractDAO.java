package project.server.hibernate.dao;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class AbstractDAO {

    Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object entity){
        getSession().persist(entity);
        logger.info("Seving", entity);
    }

    public void delete(Object entity){
        getSession().delete(entity);
    }

    public void update(Object entity){
        getSession().update(entity);
    }

}

