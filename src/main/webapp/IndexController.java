@Controller
public class IndexController {

    @Autowired
    private HotelDAO hotelDAO;

    @RequestMapping("/")
    public String index(Model model) {
        List<Hotel> hotels = hotelDAO.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "index"; // JSP file inside /WEB-INF/index.jsp
    }
}