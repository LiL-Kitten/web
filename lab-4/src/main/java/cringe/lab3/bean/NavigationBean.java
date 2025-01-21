package cringe.lab3.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@RequestScoped
@Named(value = "navigate")
public class NavigationBean implements Serializable {

    public String goToHome() {
        return "home";
    }

    public String goToCringe() {
        return "cringe";
    }
}
