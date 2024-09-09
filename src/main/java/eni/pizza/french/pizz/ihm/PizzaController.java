package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bo.Pizza;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public class PizzaController {
    @GetMapping({"add-pizza/{id}", "add-pizza"})
    public String addPizza (@PathVariable(required = false) Long id, Model model, RedirectAttributes redirectAttributes) {

        //Préparer ce que tu vas envoyer dans le formulaire par défaut
        Pizza pizza = new Pizza();

        //Si il y a un id existant dans la BDD, on récupère le film de la BDD
        //Alors on écrase le film vide qu'on voulait afficher dans le formulaire
        //Donc on affichera un film existant dans le formulaire
        if(id != 0){
            pizza = pizza.getMovieById(id);
        }


        //Data test: à commenter quand on sort de nos tests
        /*movie.setTitle("Mon Titre de film");
        movie.setYear(LocalDate.now().getYear());
        movie.setDuration(120);
        movie.setSynopsis("Il était une fois ...");
        movie.setUrlImage("mon-affiche-film.png");*/

        //Envoyer le Movie dans le front (dans la réponse) pour le mettre dans le formulaire
        //Fonctionne sur la base d'une clé en String : value
        model.addAttribute("movie", movie);

        //Envoyer les genre à la vue pour les afficher dans la liste déroulante de notre page HTML
        List<Genre> genres = genreManager.getGenres();
        model.addAttribute("genres", genres);

        //Afficher la page formulaire
        return "/V2/add-movie-page-v2";
    }


    /**
     * Traiter les données qui nous serons envoyées
     * @return
     * */
    //Postmapping obligatoire
    @PostMapping("add-movie")
    //Attention : l'ordre de @Valid et @ModelAttribute est important : TJS mettre @Valid en premier
    //ATTENTION: BindingResult bindingResult dans la liste arg doit être appelé immédiatement après le @Valid Class class,
    public String processAddMovie(@Valid @ModelAttribute(name="movie") Movie movie, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        //Mettre le movie dans la session
        //NB: si mon @ModelAttribute Movie "movieFromForm" avit pour nom "movie" , alors la ligne ci dessous ne serait pas nécessaire car elle serait faite de manière directe et cachée par le framework
        //model.addAttribute("movie", movie);

        System.out.println("Film reçu dans le Formulaire Front = " + movie);

        //Objectif tester la validité des data par contrôle de surface (=check du format uniquement)
        if (bindingResult.hasErrors()){
            System.out.println("Erreur de contrôle surface");
            //PAS DE REDIRECTION SI ERREUR FORMULAIRE
            //Car redirection avec redirect signifie nouvelle url et donc nouvelle requête et donc perte des erreurs stockées
            return "/V2/add-movie-page-v2";
        }

        //On sauvegarde le movie dans la BDD db_movie en faisant appel à la couche BLL via MovieManager
        movieManager.saveMovie(movie);

        //Ajouter un message temporaire (flash card) dans la section "flashMessage"
        /*EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous vous êtes connecté-e avec succès.");*/
        EniIHMHelpers.sendSuccessFlashMessage(redirectAttributes, String.format("Vous avez ajouté le film <%s> à la session et la BDD avec succès", movie.getTitle()));

        //Afficher la page Accueil
        return "redirect:/";
    }
}
