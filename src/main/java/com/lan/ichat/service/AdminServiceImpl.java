package com.lan.ichat.service;

import com.lan.ichat.dao.AdminMapper;
import com.lan.ichat.model.AdminEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public int insert(AdminEntity adminEntity) {
        try {
            return adminMapper.insert(adminEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(AdminEntity adminEntity) {
        try {
            return adminMapper.update(adminEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
