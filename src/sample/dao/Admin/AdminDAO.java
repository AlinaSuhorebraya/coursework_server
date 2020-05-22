package sample.dao.Admin;

import sample.Organization.Admin;

import java.util.ArrayList;

public interface AdminDAO {
    void insertAdmin(Admin admin);//вставить

    void deleteAdmin(Admin admin);

    ArrayList<Admin> getAdminList();
}
