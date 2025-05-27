package team.steelcode.steelcodebot.discord.events.slash.info.common;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class LerasiumCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "lerasium";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Mono<User> monoUser = event.getOption("user")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asUser)
                .orElse(null);

        if (monoUser == null) {
            return event.reply("User doesnt exist").withEphemeral(true);
        }

        User discUser = monoUser.block();

        return event.reply("Hey <@%s>! I see you're wondering why lerasium doesn't do what you think it should do.\n\nThis is a very common question, and the solution is very simple. Lerasium doesn't make you mistborn, but rather allows you to change your web spirit. We've adapted this by improving your Allomantic abilities (something similar to Duralumin) over time. In the future, in future versions, this will be a setting, and you'll be able to decide what you want Lerasium to do. But the progression we've been implementing for the past year isn't fun: if going into the Nether for 5 minutes means you'll finish the mod.\n\nWe hope you understand. Thank you for supporting our work :D".formatted(
                discUser.getId().asString()));
    }
}
