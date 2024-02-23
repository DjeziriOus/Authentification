package com.ACSI.Authentification.authController;

import com.ACSI.Authentification.AuthService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class authController {
    @Autowired
    AuthService authService;
    @PostMapping("/auth")
    public Result authentifier(@RequestBody AuthRequest authRequest){
        System.out.println("kamil");
        boolean result=authService.authentifier(authRequest.getEmail(),authRequest.getPassword());
        Result result1=new Result(result);
        return result1;
    }
    @GetMapping("/sendMAil")
    public String sendCode()
    {
       return "hi";
    }
    @PostMapping("/signUp")
    public Result signIn(@RequestBody SignUpRequest signUpRequest)
    {
        boolean result= authService.signIn(signUpRequest.getPassword(),signUpRequest.getEmail(),
                signUpRequest.getName());
        Result result1=new Result(result);
        return result1;
    }
    static class AuthRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Classe pour la demande d'inscription
    static class SignUpRequest {
        private String email;
        private String name;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        }
    public class Result {
        public boolean result;
        Result(boolean result)
        {
            this.result=result;
        }
    }
}
