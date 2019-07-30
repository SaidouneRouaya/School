package Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.stereotype.Controller

public class GroupsController {


    @RequestMapping("/Groups")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsList";
    }

    @RequestMapping("/englishGroup")
    public String englishGroup(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsListEnglish";
    }

    @RequestMapping("/frenchGroup")
    public String frenchGroup(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsListFrench";
    }

    @RequestMapping("/GroupDetails")
    public String groupDetails(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupDetails";
    }

    @RequestMapping("/addGroup")
    public String addGroup(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/AddGroup";
    }


}
