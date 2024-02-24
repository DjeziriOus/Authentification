package com.ACSI.Authentification.authController;

import com.ACSI.Authentification.AuthService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Window;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class authController {
    @Autowired
    AuthService authService;
    @PostMapping("/auth")
    public LogInResultObj authentifier(@RequestBody AuthRequest authRequest){
        System.out.println("kamil");
        LogInResultObj resultObj = new LogInResultObj(authService.authentifier(authRequest.getEmail(),authRequest.getPassword()).isResult() , authService.authentifier(authRequest.getEmail(),authRequest.getPassword()).isMailExists());
        System.out.println(resultObj.isResult());
        return resultObj;
    }
    @GetMapping("/sendMAil")
    public String sendCode()
    {
       return "hi";
    }
    @PostMapping("/signUp")
    public Result signIn(@RequestBody SignUpRequest signUpRequest)
    {
        boolean resultBoolean= authService.signIn(signUpRequest.getPassword(),signUpRequest.getEmail(),
                signUpRequest.getName());
        Result frontendResult=new Result(resultBoolean);
        return frontendResult;
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
    // public class resultObj{
    //     boolean result;  
    //     boolean mailExists;
    //     public resultObj(boolean result, boolean mailExists) {
    //         this.result = result;
    //         this.mailExists = mailExists;
    //     }
    // }
    public class LogInResultObj{
        boolean result;  
        boolean mailExists;
        public LogInResultObj(boolean result, boolean mailExists) {
            this.result = result;
            this.mailExists = mailExists;
        }
        public boolean isResult() {
            return result;
        }
        public void setResult(boolean result) {
            this.result = result;
        }
        public boolean isMailExists() {
            return mailExists;
        }
        public void setMailExists(boolean mailExists) {
            this.mailExists = mailExists;
        }
    }
}
