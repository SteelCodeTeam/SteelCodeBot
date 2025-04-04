package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;
import team.steelcode.steelcodebot.persistence.entity.UserEntity;
import team.steelcode.steelcodebot.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@Log
public class AddContributionCommand implements ISlashCommand {
    private final UserRepository userRepository;

    public AddContributionCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getName() {
        return "add_contribution";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        Mono<User> monoUser = event.getOption("user")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asUser)
                .orElse(null);

        Long points = event.getOption("points")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElse(null);

        if (points == null || points < 0) {
            return event.reply("Invalid points").withEphemeral(true);
        }

        if (monoUser == null) {
            return event.reply("User doesnt exist").withEphemeral(true);
        }

        User discUser = monoUser.block();

        Optional<UserEntity> optUser = userRepository.findById(discUser.getId().asLong());
        if (optUser.isPresent()) {
            UserEntity appUser = optUser.get();
            appUser.setPoints(appUser.getPoints() + points);

            UserEntity savedUser = userRepository.save(appUser);

            return event.reply("<@%s> has %s points. Thanks for all of your contributions!".formatted(
                    savedUser.getId(), savedUser.getPoints()));
        } else {
            UserEntity appUser = new UserEntity();
            appUser.setId(discUser.getId().asLong());
            appUser.setName(discUser.getUsername());
            appUser.setPoints(points);

            UserEntity savedUser = userRepository.save(appUser);

            return event.reply("<@%s> has %s points. Thanks for all of your contributions!".formatted(
                    savedUser.getId(), savedUser.getPoints()));
        }
    }
}
