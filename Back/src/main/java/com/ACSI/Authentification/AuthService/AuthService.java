package com.ACSI.Authentification.AuthService;

import com.ACSI.Authentification.authController.authController;
import com.ACSI.Authentification.authModel.AuthModel;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    @Autowired
    private AuthModel authModel;

    public class logInResultObj{
        boolean result;  
        boolean mailExists;
        public logInResultObj(boolean result, boolean mailExists) {
            this.result = result;
            this.mailExists = mailExists;
        }
        public boolean isResult() {
            return result;
        }
        public boolean isMailExists() {
            return mailExists;
        }
        public void setResult(boolean result) {
            this.result = result;
        }
        public void setMailExists(boolean mailExists) {
            this.mailExists = mailExists;
        }
    }
    
    public logInResultObj authentifier(String mail,String password)
    {
        String stored_password=authModel.getPassword(mail);
        if(stored_password.isEmpty())
            return new logInResultObj(false,false);
        if(password.equals(stored_password))
            return new logInResultObj(true,true);
        else return new logInResultObj(false,true);
    }

    public boolean signIn(String password,String mail,String name)
    {
        if (!authModel.verify(mail))
            return false;    
        if (password == ""|| name == "") return false;
        return authModel.insert(mail,name,password);
    }
}
