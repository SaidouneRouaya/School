package DAO;

import Entities.Staff;



import java.util.List;

public interface StaffDAO {
    public void addStaff(Staff staff);
    public List<Staff> getAllStaffs();
    public void deleteStaff(int id);
    public void updateStaff(int id, String type);
}
