package Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller

public class ModulesController {


    @RequestMapping("/Modules")
    public String modulesList(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Modules/ModulesDataTable";
    }

    @RequestMapping("/addModule")
    public String addModule(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Modules/AddModule";
    }



}
