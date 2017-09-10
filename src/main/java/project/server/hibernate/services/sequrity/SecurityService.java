package project.server.hibernate.services.sequrity;



public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
