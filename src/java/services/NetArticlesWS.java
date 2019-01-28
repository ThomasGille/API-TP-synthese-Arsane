package services;

import dal.Achete;
import dal.AchetePK;
import dal.Article;
import dal.Auteur;
import dal.Categorie;
import dal.Client;
import dal.Domaine;
import dal.PartAchete;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.AcheteFacade;
import session.ArticleFacade;
import session.AuteurFacade;
import session.CategorieFacade;
import session.ClientFacade;
import session.DomaineFacade;
import tools.Utility;

@Path("webservice")
public class NetArticlesWS {

    @EJB
    ClientFacade clientF;
    @EJB
    ArticleFacade articleF;
    @EJB
    DomaineFacade domaineF;
    @EJB
    AcheteFacade acheteF;
    @EJB
    CategorieFacade categorieF;
    @EJB
    AuteurFacade auteurF;

    public NetArticlesWS() {
    }

    @GET
    @Path("connecter/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecter(@PathParam("login") String login) {
        Response response = null;
        try {
            Client client = clientF.lireLogin(login);
            response = Response.status(Response.Status.OK).entity(client).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("auteur/connecter/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecterAuteur(@PathParam("login") String login) {
        Response response = null;
        try {
            Auteur auteur = auteurF.lireLogin(login);
            response = Response.status(Response.Status.OK).entity(auteur).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("article/redige/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuteursFromArticle(@PathParam("id") Integer id) {
        Response response = null;
        try {
            GenericEntity<List<Auteur>> auteurs = new GenericEntity<List<Auteur>>(auteurF.getAuteursByArticle(id)) {
            };
            response = Response.status(Response.Status.OK).entity(auteurs).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("article/last")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestArticle() {
        Response response = null;
        try {
            Article article = articleF.getLatest();
            //on modifie le header Content-Type pour que celui ci précise que les strings sont encodeées en UTF-8
            response = Response.status(Response.Status.OK).entity(article).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("domaines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomaines() {
        Response response = null;
        try {
            GenericEntity<List<Domaine>> domaines = new GenericEntity<List<Domaine>>(domaineF.lister()) {
            };
            response = Response.status(Response.Status.OK).entity(domaines).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("article/domaine/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticlesByDomain(@PathParam("id") Integer id) {
        Response response = null;
        try {
            GenericEntity<List<Article>> articles = new GenericEntity<List<Article>>(articleF.listerByDomaine(id)) {
            };
            response = Response.status(Response.Status.OK).entity(articles).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("achat/client/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAchatsByClient(@PathParam("id") Integer id) {
        Response response = null;
        try {
            GenericEntity<List<Achete>> achats = new GenericEntity<List<Achete>>(acheteF.listerByClient(id)) {
            };
            response = Response.status(Response.Status.OK).entity(achats).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("article/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam("id") Integer id) {
        Response response = null;
        try {
            Article article = articleF.lire(id);
            response = Response.status(Response.Status.OK).entity(article).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("domaine/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomaine(@PathParam("id") Integer id) {
        Response response = null;
        try {
            Domaine domaine = domaineF.lire(id);
            response = Response.status(Response.Status.OK).entity(domaine).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("client/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@PathParam("id") Integer id) {
        Response response = null;
        try {
            Client client = clientF.lire(id);
            response = Response.status(Response.Status.OK).entity(client).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("auteur/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuteur(@PathParam("id") Integer id) {
        Response response = null;
        try {
            Auteur auteur = auteurF.lire(id);
            response = Response.status(Response.Status.OK).entity(auteur).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("acheter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acheter(AchetePK achetePK) {
        Response response = null;
        try {
            int idClient = achetePK.getIdClient();
            int idArticle = achetePK.getIdArticle();
            Date dateAchat = new Date();
            Article article = articleF.lire(achetePK.getIdArticle());
            clientF.debiterCompte(idClient, article.getPrix());
            acheteF.effectuerAchat(idClient, idArticle, dateAchat);
            response = Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("client")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerClient(Client client) {
        Response response = null;
        try {
            clientF.ajouter(client);
            response = Response.status(Response.Status.OK).entity(client).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @PUT
    @Path("client")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifierClient(Client client) {
        Response response = null;
        try {
            clientF.modifier(client);
            response = Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        Response response = null;
        try {
            GenericEntity<List<Categorie>> categories = new GenericEntity<List<Categorie>>(categorieF.lister()) {
            };
            response = Response.status(Response.Status.OK).entity(categories).header("Content-Type", "application/json; charset=UTF-8").build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("categorie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorie(@PathParam("id") Integer id) {
        Response response = null;
        try {
            Categorie categorie = categorieF.lire(id);
            response = Response.status(Response.Status.OK).entity(categorie).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    

    @GET
    @Path("achat/auteur/{idAuteur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAchatsAuteur(@PathParam("idAuteur") String textIdAuteur) throws Exception {
        Response response = null;
        try {
            int idAuteur = Integer.parseInt(textIdAuteur);
            // List<Achete> list = auteurF.getAchats(idAuteur);
            List<Achete> list = auteurF.getAchatsByAuteur(idAuteur);
            GenericEntity<List<Achete>> lAchats = new GenericEntity<List<Achete>>(list) {
            };
            response = Response.status(Response.Status.OK).entity(lAchats).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }    
    
    @GET
    @Path("achat/part/{idAuteur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAchatsWithShare(@PathParam("idAuteur") String textIdAuteur) throws Exception {
        Response response = null;
        try {
            int idAuteur = Integer.parseInt(textIdAuteur);
            List<PartAchete> list = auteurF.getAchatsWithShares(idAuteur);
            GenericEntity<List<PartAchete>> lAchatsPart = new GenericEntity<List<PartAchete>>(list) {
            };
            response = Response.status(Response.Status.OK).entity(lAchatsPart).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utility.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }    
}
