package com.lan.ichat.service;

import com.lan.ichat.model.Admin;

public interface AdminService {

    Admin getAdminByUsername(String username);

    int insert(Admin admin);

    int update(Admin admin);
}
