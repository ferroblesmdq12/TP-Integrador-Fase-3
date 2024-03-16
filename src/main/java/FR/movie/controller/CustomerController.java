package FR.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie-app")
public class CustomerController {

    /* Probamos 2 endpoints uno con seguridad y otro sin seguridad */
    /* Esto funciona si "comentamos" en el archivo pom.xml
     * la dependencia de "SPRINGBOOT SECURITY", entonces no habrá
     * seguridad en nuestro proyecto.  */

    /* ENDPOINT con Seguridad */
    @GetMapping("/index")
    public String index(){
        return "Aplicando Seguridad";
        //localhost:8080/movie-app/index
    }

    /* ENDPOINT sin Seguridad */
//    @GetMapping("/index2")
//    public String index2(){
//        return "Not SECURED!";
//        //localhost:8080/movie-app/index2
//    }


    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession(){
        String sessionId = "";
        User userObject = null;

        // Obtén todas las sesiones activas
        List<Object> sessions = sessionRegistry.getAllPrincipals();

        for (Object session : sessions) {
            if (session instanceof User) {

                User user = (User) session;
                userObject = user;

                List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(user, false);

                // Aquí puedes realizar las operaciones que necesites con la lista de sesiones
                for (SessionInformation sessionInformation : sessionInformationList) {

                    // Accede a la información de cada sesión
                    sessionId = sessionInformation.getSessionId();
                }
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Hello world!");
        response.put("sessionId", sessionId);
        response.put("session", userObject);

        return ResponseEntity.ok(response);
    }

}
