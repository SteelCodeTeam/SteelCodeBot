package team.steelcode.steelcodebot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SteelCodeBotApplication {

    @Value("${discord4j.token}")
    private static String TOKEN;

    public static void main(String[] args) {
        SpringApplication.run(SteelCodeBotApplication.class, args);
    }

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create("MTM1NzA4NjAxNDAzNTk4NDU0NA.G1al6z.85szE5-kqvIQuhJXYbpVXLK50f_Q_Ds0nLzfXA").build()
                .gateway()
                .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.watching( "como programa SteelCodeTeam")))
                .login()
                .block();
    }

    @Bean
    public RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }
}
