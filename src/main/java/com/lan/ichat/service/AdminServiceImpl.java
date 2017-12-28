package com.lan.ichat.service;

import com.lan.ichat.dao.AdminMapper;
import com.lan.ichat.model.AdminEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;


    @Override
    public AdminEntity getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    @Override
    public int insert(AdminEntity adminEntity) {
        return adminMapper.insert(adminEntity);
    }

    @Override
    public int update(AdminEntity adminEntity) {
        return adminMapper.update(adminEntity);
    }
}
