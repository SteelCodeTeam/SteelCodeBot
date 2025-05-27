package team.steelcode.steelcodebot.discord.events.slash.info.projects;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class ThisBotCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "steelcodebot";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String message = """
                ## SteelCodeBot!
                
                It's the bot you're using right now.
                
                > Github: [SteelCodeBot](<https://github.com/SteelCodeTeam/SteelCodeBot>)
                """;

        return event.reply().withContent(message);
    }
}
