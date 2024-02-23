package com.ACSI.Authentification.AuthService;

import com.ACSI.Authentification.authModel.AuthModel;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    @Autowired
    private AuthModel authModel;


    public boolean authentifier(String mail,String password)
    {

        String stored_password=authModel.getPassword(mail);
        if(stored_password.isEmpty())
            return false;
        if(password.equals(stored_password))
            return true;
        else return false;
    }
    public boolean signIn(String password,String mail,String name)
    {
        if (!authModel.verify(mail))
            return false;

        return authModel.insert(mail,name,password);
    }
}
