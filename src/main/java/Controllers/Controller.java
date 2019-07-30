package Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller

public class Controller {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/login2.j";
    }

  /*  @RequestMapping("/index")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "index";
    }*/
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
        return "LanguagesSchoolPages/Groups/GroupsList";
    }


}
