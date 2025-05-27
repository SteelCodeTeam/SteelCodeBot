package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;
import team.steelcode.steelcodebot.persistence.entity.UserEntity;
import team.steelcode.steelcodebot.persistence.repository.UserRepository;

import java.util.Optional;

@Service
public class GetContributionsCommand implements ISlashCommand {
    private final UserRepository userRepository;

    public GetContributionsCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getName() {
        return "get_contribution";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Mono<User> monoUser = event.getOption("user")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asUser)
                .orElse(null);

        if (monoUser == null) {
            return event.reply("User doesn't exists.").withEphemeral(true);
        }

        User discUser = monoUser.block();

        Optional<UserEntity> optUser = userRepository.findById(discUser.getId().asLong());
        if (optUser.isPresent()) {
            UserEntity appUser = optUser.get();

            return event.reply("<@%s> has %s points. Thanks for all of your contributions!".formatted(
                    appUser.getId(), appUser.getPoints()));
        } else {
            return event.reply("<@%s> doesn't have contributions points yet!".formatted(discUser.getId().asLong()));
        }
    }
}
