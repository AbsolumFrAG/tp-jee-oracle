package com.jevendstout.api.controller;

import com.jevendstout.api.entity.Article;
import com.jevendstout.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour gérer les requêtes liées aux articles.
 * Fournit des endpoints pour les opérations CRUD sur les articles.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Constructeur pour injecter le service des articles.
     * @param articleService Le service à injecter pour la gestion des articles.
     */
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Récupère tous les articles.
     *
     * @return Une liste d'articles.
     */
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.findAllArticles();
    }

    /**
     * Récupère un article par son identifiant.
     *
     * @param id L'identifiant de l'article à récupérer.
     * @return Une réponse contenant l'article trouvé ou un statut 'not found'.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.findArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créé un nouvel article.
     *
     * @param article Les détails de l'article à créer.
     * @return L'article créé.
     */
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    /**
     * Met à jour l'article existant.
     *
     * @param id Les détails de l'article à mettre à jour.
     * @param articleDetails L'identifiant de l'article à mettre à jour.
     * @return Une réponse content l'article mis à jour ou un statut 'not found'.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        return articleService.findArticleById(id)
                .map(article -> {
                    article.setNom(articleDetails.getNom());
                    article.setDescription(articleDetails.getDescription());
                    article.setPrix(articleDetails.getPrix());
                    article.setCategorie(articleDetails.getCategorie());
                    Article updatedArticle = articleService.saveArticle(article);
                    return ResponseEntity.ok(updatedArticle);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un article.
     *
     * @param id L'identifiant de l'article à supprimer.
     * @return Une réponse vide avec un statut 'ok' ou 'not found'.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        return articleService.findArticleById(id)
                .map(article -> {
                    articleService.deleteArticle(article.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
