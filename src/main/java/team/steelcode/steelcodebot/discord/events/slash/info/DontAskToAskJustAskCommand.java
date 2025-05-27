package team.steelcode.steelcodebot.discord.events.slash.info;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;


@Service
public class DontAskToAskJustAskCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "just_ask";
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

        return event.reply("Hey <@%s>! Don't ask to ask, just ask!\n*More info: <https://dontasktoask.com>*".formatted(
                discUser.getId().asString()));
    }
}
