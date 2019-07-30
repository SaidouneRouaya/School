package DAO;

import Entities.Profile;

import java.util.List;

public interface ProfileDAO {

    public void addProfile(Profile profile);
    public List<Profile> getAllProfiles();
    public void deleteProfile(int id);
    public void updateProfile(int id, String type);

}
