package models;

import lombok.Data;

@Data
public class UsersResponseModel {
    String name, job, id, createdAt, updatedAt;
    ResourceListData data;
    ResourceListSupport support;
}
