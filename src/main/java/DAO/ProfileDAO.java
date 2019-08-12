package DAO;

import Entities.Profile;

import java.util.List;

public interface ProfileDAO {

    public void addProfile(Profile profile);
    public List<Profile> getAllProfiles();
    public void deleteProfile(int id);
    public Profile getProfileByID (int id);
    public List<Profile>  getProfileByEmail (String email, String password);
    public void updateProfile(int id, Profile newProfile);

}
