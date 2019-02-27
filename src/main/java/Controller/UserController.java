package Controller;

<<<<<<< HEAD
import Po.User;
import Service.UserService;
=======
import Po.Goods;
import Po.User;
import Service.UserService;
import com.sun.deploy.net.HttpResponse;
>>>>>>> cf0d4c6d7e7a9144a7d0ce573861bc9c6bd8d597
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
<<<<<<< HEAD
=======
import javax.servlet.ServletException;
>>>>>>> cf0d4c6d7e7a9144a7d0ce573861bc9c6bd8d597
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String name, String password, HttpServletResponse response, HttpServletRequest httpServletRequest){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        if(userService.isNamePassword(user)){
            Cookie cookie = new Cookie("WarehouseUser","admin");
            cookie.setMaxAge(60*60*24*7);   //有效7天
            response.addCookie(cookie);
            httpServletRequest.getSession().setAttribute("user",user);
            return "main";
        }
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest httpServletRequest,HttpServletResponse response){
        httpServletRequest.getSession().removeAttribute("user");
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies ){
            if(cookie.getName().equals("WarehouseUser")){
                cookie.setMaxAge(0);   //设置Cookie有效时间为0  相当于清除Cookie
<<<<<<< HEAD
=======
                cookie.setPath("/");
>>>>>>> cf0d4c6d7e7a9144a7d0ce573861bc9c6bd8d597
                response.addCookie(cookie);
            }
        }
        return "login";
    }

    @RequestMapping(value = "index")
    public String Cookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("WarehouseUser")){
                    String name = cookie.getValue();
                    User user= userService.findUserByName(name);
                    request.getSession().setAttribute("user",user);
                    return "main";
                }
            }
        }
        return "login";
    }

    @RequestMapping(value = "user/updPassword", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updPassword(String name,String oldPassword,String newPassword){
        if (oldPassword != newPassword && newPassword != name)
            return "error";
<<<<<<< HEAD
        User user = userService.findUserByName(name);
        if (user!=null) {
=======
       User user = userService.findUserByName(name);
       if (user!=null) {
>>>>>>> cf0d4c6d7e7a9144a7d0ce573861bc9c6bd8d597
           if (user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);
                if(userService.updUser(user)>0)
                    return "ok";
           }
<<<<<<< HEAD
        }
        return "error";
=======
       }
       return "error";
>>>>>>> cf0d4c6d7e7a9144a7d0ce573861bc9c6bd8d597
    }

}
