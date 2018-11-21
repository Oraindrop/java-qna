package codesquad.user;

import codesquad.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String create(User user){
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping
    public String showUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable long id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String showUpdateView(@PathVariable long id, HttpSession session){
        if(!HttpSessionUtils.existLoginUserFromSession(session)) return "redirect:/user/login";
        if(!HttpSessionUtils.getLoginUserFromSession(session).matchId(id)) return "user/list_failed";
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable long id, User modifiedUser, HttpSession session){
        if(!HttpSessionUtils.existLoginUserFromSession(session)) return "redirect:/user/login";
        if(!HttpSessionUtils.getLoginUserFromSession(session).matchId(id)) return "user/list_failed";

        User user = userRepository.findById(id).orElse(null);
        if(!user.matchPassword(modifiedUser)) return "user/updateForm_failed";
        user.update(modifiedUser);
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session){
        User user = userRepository.findByUserId(userId).orElse(null);
        if(user == null || !user.matchPassword(password)) return "user/login_failed";

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }
}
