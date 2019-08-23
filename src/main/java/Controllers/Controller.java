package Controllers;

import DAO.ProfileDAO;
import Entities.Profile;
import Util.utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller

@SessionAttributes("sessionUser")
public class Controller {



    public Profile profile;


    @ModelAttribute("sessionUser")
    public void setUpUserForm(Model model) {model.addAttribute("sessionUser", profile);
    }

    @Autowired
    ProfileDAO profileDAO;

    @RequestMapping("")
    public String redirect() {
        return "redirect:/login2.j";
    }

    @RequestMapping(value="/loginprocess")
    public String loginsucess(Model model, @RequestParam String username,
                              @RequestParam String password, @ModelAttribute ("sessionUser") Profile  profile)
    {
        String pageretour = "redirect:/error.j";


        ModelAndView modelview = new ModelAndView();

        List<Profile> users= profileDAO.getProfileByEmail(username, password);


        if (users != null && users.size()!=0) {
            Profile user = users.get(0);

            System.out.println(user.getType());
            if (user.getUsername().equals(username)) {


                if (user.getPassword().equals(utilities.hashPassword(password))) {
                    this.profile = user;
                    modelview.addObject("sessionUser", this.profile);
                    model.addAttribute("sessionUser", this.profile);
                    model.addAttribute("profile", profile);

                    if (user.getType().equalsIgnoreCase("Admin"))   pageretour = "redirect:Home.j";

                    else if (user.getType().equalsIgnoreCase("Receptionist")) pageretour = "redirect:Home.j";

                }
                else {
                    model.addAttribute("erreur", "erreur dans l'email ou le mot de passe");
                }
            }
        }
        else
        {
            model.addAttribute("erreur", "Utilisateur inexistant !");
        }

        return pageretour;
    }

    @RequestMapping("/deconnexion")

    public String logout(Model model, @SessionAttribute("sessionUser") Profile profile,  SessionStatus status)
    {
        profile=null;
        this.profile = null;

        status.setComplete();
        return "redirect:/login2.j";
    }

    public  Boolean isConnected()
    {
        return profile != null;
    }

  //Todo : load all data when first login



   @RequestMapping("/login")
    public String login(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "login2";
    }

    @RequestMapping("/login2")
    public String login2(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "login2";
    }

    @RequestMapping("/index")
    public String pageAccueil(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        System.out.println("im an admin");
        System.out.println(profile.getType());

        model.addAttribute("error", error);

        return "redirect:/addStudent.j";
    }
    @RequestMapping("/Home")
    public String Home(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";


        System.out.println(profile.getType());
        model.addAttribute("profile", profile);

        model.addAttribute("error", error);

        return "LanguagesSchoolPages/home";
    }

    @RequestMapping("/indexReceptionist")
    public String pageAccueil2(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        System.out.println("im a receptionsit");
        model.addAttribute("error", error);
        return "redirect:/addStudent.j";
    }
    @RequestMapping("/error")
    public String error(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/404";
    }
    @RequestMapping("/empty")
    public String empty(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/empty";
    }



}
