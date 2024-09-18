package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.IAuthentificationManager;
import eni.pizza.french.pizz.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@SessionAttributes({"connectedUser"})
@Controller
public class UtilisateurController {
    @Autowired
    IAuthentificationManager authentificationManager;
    @GetMapping("login")
    public String login (Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateurConnected = (Utilisateur) model.getAttribute("connectedUser");
        if (utilisateurConnected != null) {
            System.out.println("Vous êtes déjà connecté");
            return "redirect:/home";
        }
        return "login-page";
    }
    @GetMapping("user-connected")
    public String userConnected(Principal principal, Model model, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        Utilisateur utilisateur = authentificationManager.getConnectedUtilisateurs(email);
        if (utilisateur == null) {
            System.out.println("Vous n'avez pas réussi à vous connecter");
            return "redirect:/login";
        }
        model.addAttribute("utilisateur",utilisateur);
        System.out.printf("L'utilisateur ayant l'email %s est connecté.",utilisateur.getEmail());
        return "redirect:/home";
    }
    @PostMapping("login")
    public String processLogin(@Valid @ModelAttribute(name="utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            System.out.println("Erreur de contrôle surface");
            return "login-page";
        }
        authentificationManager.authenticate(utilisateur.getEmail(), utilisateur.getPassword());
        return "redirect:/";
    }

    @GetMapping("add-user")
    public String addUser (Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = new Utilisateur();


        model.addAttribute("utilisateur",utilisateur);

        return "add-users";

    }
    @PostMapping("add-user")
    public String processAddUser(@Valid @ModelAttribute(name="utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Erreur de contrôle surface");
            return "add-users";
        }
        model.addAttribute("utilisateur",utilisateur);

        authentificationManager.saveUtilisateur(utilisateur);
        return "redirect:/";

    }
    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Model model) {
        sessionStatus.setComplete();
        System.out.printf("Vous vous êtes déconnectés");
        return "redirect:/login";
    }
}
