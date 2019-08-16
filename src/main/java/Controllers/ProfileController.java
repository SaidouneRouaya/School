package Controllers;


import DAO.ProfileDAO;

import Entities.Profile;

import Util.utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@org.springframework.stereotype.Controller


public class ProfileController {



    @Autowired
    ProfileDAO profileDAO;

    @RequestMapping("/Profiles")
    public String ProfileList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("profilesList", profileDAO.getAllProfiles());
                model.addAttribute("error", error);
                model.addAttribute("profile", profile);
                return "LanguagesSchoolPages/Profile/ProfilesDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


    @RequestMapping("/addProfile")
    public String addProfile(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        model.addAttribute("error", error);

        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("profile", profile);
                return "LanguagesSchoolPages/Profile/AddProfile";

            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }

    }

    @RequestMapping("/updateUserProfile")
    public String updateProfile(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("userProfile", profileDAO.getProfileByID(Integer.parseInt(query)));

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Profile/UpdateUserProfile";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


    @RequestMapping("/addNewProfile")
    public String addNewProfile(Model model, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                String password= utilities.hashPassword(param.get("password") );

                Profile userProfile = new Profile(param.get("name"), param.get("familyName"),param.get("r3"),param.get("picture").getBytes(),
                        param.get("username"),password );

                profileDAO.addProfile(userProfile);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:Profiles.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/deleteProfile")
    public String deleteProfile(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {


                profileDAO.deleteProfile(Integer.parseInt(query));

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:Profiles.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/PersistUpdateProfile")
    public String persistUpdateProfile(Model model, @RequestParam String query, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";


        if (profile != null) {
            if (profile.getType().equals("Admin")) {
                Profile userProfile;

                if (!param.get("password").isEmpty())

                {

                    String password= utilities.hashPassword(param.get("password") );

                    userProfile   = new Profile(param.get("name"), param.get("familyName"),param.get("r3"),param.get("picture").getBytes(),
                            param.get("username"), password );
                }
                else{
                  userProfile = new Profile(param.get("name"), param.get("familyName"),param.get("r3"),param.get("picture").getBytes(),
                            param.get("username"));
                }

                System.out.println("#\n"+userProfile.getType());
                System.out.println(userProfile.getUsername());
                System.out.println(userProfile.getPassword());


                profileDAO.updateProfile(Integer.parseInt(query), userProfile);


                model.addAttribute("userProfile", userProfile);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:Profiles.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }





}
