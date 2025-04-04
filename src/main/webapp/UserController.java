@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {

        boolean isValid = userDAO.validateLogin(email, password);

        if (isValid) {
            // You can store user info in session here if needed
            return "redirect:/dashboard"; // or another secured page
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // back to login.html
        }
    }
}
