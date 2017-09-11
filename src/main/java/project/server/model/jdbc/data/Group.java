package project.server.model.jdbc.data;

import project.server.hibernate.entities.GroupsEntity;
import project.server.hibernate.entities.UsersEntity;

import java.io.Serializable;

public class Group extends Entity implements Serializable{             //Класс для описания групп

    private static int id_count;
    private Integer id;
    private String name;

    public GroupsEntity groupsEntity(UsersEntity usersEntity){
        GroupsEntity groupsEntity = new GroupsEntity();
        if (id!=null) {
            groupsEntity.setId(id);
        }
        if (name!=null) {
            groupsEntity.setName(name);
        }
        groupsEntity.setUser_id(usersEntity);
        return groupsEntity;
    }

    public Group(GroupsEntity groupsEntity){
        id = groupsEntity.getId();
        name = groupsEntity.getName();
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
        --id_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Индекс: " + id.toString() +
                " Название: " + name + '\'' +
                "\n";
    }

    public static int getId_count() {
        return id_count;
    }

    public static void setId_count(int id_count) {
        Group.id_count = id_count;
    }
}
