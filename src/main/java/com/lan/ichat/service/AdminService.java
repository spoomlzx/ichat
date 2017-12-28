package com.lan.ichat.service;

import com.lan.ichat.model.AdminEntity;

public interface AdminService {

    AdminEntity getAdminByUsername(String username);

    int insert(AdminEntity adminEntity);

    int update(AdminEntity adminEntity);
}
