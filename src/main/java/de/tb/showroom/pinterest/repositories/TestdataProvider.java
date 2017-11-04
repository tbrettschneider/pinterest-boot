package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.Pinboard;
import de.tb.showroom.pinterest.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
class TestdataProvider implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = Logger.getLogger(TestdataProvider.class.getName());

    private UserRepository userRepository;

    TestdataProvider(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("PinboardRepository cannot be null");
        }
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("setup testdata...");

        User user = new User("heisseherdplatte");

        Pinboard pinboard = new Pinboard("Delicious vegetables for any occasion!");
        pinboard.addPin("https://i.imgur.com/rKHj4bl.jpg", "Lovely Keisha...");
        pinboard.addPin("https://i.pinimg.com/originals/e1/cc/96/e1cc969111be56bab422369a0b8210c3.jpg");
        pinboard.addPin("http://i.imgur.com/w2Blryu.jpg", "Say hello to those boobies");
        pinboard.addPin("http://pre14.deviantart.net/d711/th/pre/f/2015/140/9/d/cosplay07896_keisha_grey_by_cruelroin-d8u1u25.jpg", "All good things are three!");
        user.addPinboard(pinboard);

        pinboard = new Pinboard("Cars are the stars");
        pinboard.addPin("http://www.legendarymotorcar.com/images/Vehicles/2016/11/1969-Ford-Mustang-BOSS-429-1617-30.jpg", "Beautiful Mustang");
        user.addPinboard(pinboard);

        userRepository.save(user);
    }
}
