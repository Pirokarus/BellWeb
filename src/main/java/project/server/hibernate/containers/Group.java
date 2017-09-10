package project.server.hibernate.containers;

import project.server.hibernate.entities.GroupsEntity;

public class Group {
    private int id;
    private String name;

    public Group(GroupsEntity groupsEntity) {
        this.id = groupsEntity.getId();
        this.name = groupsEntity.getName();
    }

    public GroupsEntity groupsEntity(){
        GroupsEntity groupsEntity = new GroupsEntity();
        groupsEntity.setId(this.id);
        groupsEntity.setName(this.name);
        return groupsEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
