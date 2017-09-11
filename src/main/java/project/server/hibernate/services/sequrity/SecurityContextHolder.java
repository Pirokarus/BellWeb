package project.server.hibernate.services.sequrity;

public class SecurityContextHolder {
    private static final ThreadLocal<Integer> threadLocalScope = new  ThreadLocal<>();

    public final static Integer getLoggedUser() {
        return threadLocalScope.get();
    }

    public final static void setLoggedUser(Integer user) {
        threadLocalScope.set(user);
    }
}
