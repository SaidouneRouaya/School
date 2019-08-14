package Controllers;

import DAO.ProfileDAO;
import Entities.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller

@SessionAttributes("utilisateur")
public class Controller {



    public Profile profile;


    @ModelAttribute("utilisateur")
    public void setUpUserForm(Model model) {model.addAttribute("utilisateur", profile);
    }

    @Autowired
    ProfileDAO profileDAO;

    @RequestMapping("")
    public String redirect() {
        return "redirect:/login2.j";
    }

    @RequestMapping(value="/loginprocess")
    public String loginsucess(Model model, @RequestParam String username,
                              @RequestParam String password, @ModelAttribute ("utilisateur") Profile  profile)
    {
        String pageretour = "";
        System.out.println(username+" + "+password);

        ModelAndView modelview = new ModelAndView();

        List<Profile> users= profileDAO.getProfileByEmail(username, password);

        if (users != null && users.size()!=0) {
            Profile user = users.get(0);
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {

                   if (user.getType().equalsIgnoreCase("Admin"))   pageretour = "redirect:index.j";
                   else if (user.getType().equalsIgnoreCase("Receptionist")) pageretour = "redirect:indexReceptionist.j";

                    this.profile = user;
                    modelview.addObject("utilisateur", this.profile);
                    model.addAttribute("utilisateur", this.profile);
                    System.out.println(this.profile.getType());

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

    public String logout(Model model, @ModelAttribute("utilisateur") Profile profile,  SessionStatus status)
    {
        this.profile = null;

        status.setComplete();
        return "login2";
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
        return "login";
    }

    @RequestMapping("/login2")
    public String login2(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "login2";
    }

    @RequestMapping("/index")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "redirect:/Groups.j";
    }

    @RequestMapping("/indexReceptionist")
    public String pageAccueil2(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "index-receptionist";
    }
    @RequestMapping("/error")
    public String error(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/404";
    }


}
