package ng.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ng.Entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
public class Controller {




    @CrossOrigin
    @PostMapping(value = "/authenticate")
    public int validateToken(@RequestBody String token) {
        String[] splitUp = token.split("\"");
        String username = splitUp[3];
        String password = splitUp[7];
        User user = new User(username, password);
        return ng.DAO.UserDao.validateLogin(user);
    }


    //Search Routes
    @RequestMapping(value = "/searchSingerName/{name}", method = RequestMethod.GET)
    public Singer getSingerByName(@PathVariable("name") String name) {
        return ng.DAO.SingerDao.getSingerByName(name);
    }

    @RequestMapping(value = "/searchAlbumName/{name}", method = RequestMethod.GET)
    public Album getAlbumByName(@PathVariable("name") String name) {
        return ng.DAO.AlbumDao.getAlbumByName(name);
    }


    //Implemented early on to learn Spring
    @RequestMapping(value = "/singers", method = RequestMethod.GET)
    public Collection<Singer> getAllSingers() {
        return ng.DAO.SingerDao.getAllSingers();
    }

}
