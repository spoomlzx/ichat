package com.lan.ichat.service;

import com.lan.ichat.dao.AdminMapper;
import com.lan.ichat.model.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;


    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    @Override
    @Transactional
    public int insert(Admin admin) {
        try {
            return adminMapper.insert(admin);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(Admin admin) {
        try {
            return adminMapper.update(admin);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
