package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolmanConfiguration {

    @Bean
    public Holman holman() {
        Holman holman = new Holman();
        holman.setHowLong(5);
        holman.setName("test");
        return holman;
    }
}
