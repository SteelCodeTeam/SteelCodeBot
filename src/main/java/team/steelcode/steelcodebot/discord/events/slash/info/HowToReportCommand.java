package team.steelcode.steelcodebot.discord.events.slash.info;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class HowToReportCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "how_to_report";
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

        String message = """
                **Hey <@%s>!**
                ### Do you need report a bug?
                Go to forum and post a new topic with the following template:
                ```dsconfig
                **Title:** [BUG] TEXT
                
                **Mod Version:** VERSION

                **Description:** TEXT AND SCREENSHOTS
                
                **Steps to reproduce:** TEXT AND SCREENSHOTS
                ```
                ### Do you need to do a suggestion?
                Go to forum and post a new topic with the following template:
                ```dsconfig
                **Title:** [IMPROVEMENT] TEXT
                
                **Description:** TEXT
                
                **Expected behaviour:** TEXT
                ```
                *Thanks for your interest in our project!*
                """.formatted(discUser.getId().asString());

        return event.reply(message);
    }
}
