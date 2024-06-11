package school.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import school.dto.UserDTO;
import school.model.auth.User;
import school.repository.UserRepository;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;
    // private EmailService emailService;

    //public ViewController(EmailService emailService) {
    // this.emailService = emailService;
    //}

    @RequestMapping("/login")
    public String login() {

        System.out.println("ViewController.login");
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }



   /* @RequestMapping("/validation")
    public String getValidationCode(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("User: " + user);
        emailService.setUser((school.model.auth.User) user);
        String response = emailService.sendEmail();
        System.out.println("Response from SendGrid: " + response);
        return "validation";
    }*/

    @GetMapping("/account-security")
    @ResponseBody
    public UserDTO account(HttpSession session) {
        User user = (User) session.getAttribute("user");
        User actualUser = userRepository.findByEmail(user.getEmail());
        System.out.println("User: " + actualUser);
        if (user != null) {
            UserDTO userDTO = new UserDTO((school.model.auth.User) actualUser);
            return userDTO;
        }
        return null;
    }


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/bursa")
    public String bursa() {
        return "bursa";
    }
    @GetMapping("/docs")
    public String docs() {
        return "docs";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }


}
