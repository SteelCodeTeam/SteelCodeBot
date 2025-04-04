package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.Id;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;
import team.steelcode.steelcodebot.persistence.entity.UserEntity;
import team.steelcode.steelcodebot.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PromoteCommand implements ISlashCommand {

    private final UserRepository userRepository;

    private final Long SteelCodeRole = 1054472540485066752L;
    private final Long DiscordStaffRole = 1054457658716979231L;
    private final Long CollaboratorRole = 1054458432251494480L;
    private final Long HelperRole = 1357224459085414440L;

    public PromoteCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getName() {
        return "promote";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        Optional<UserEntity> userEntityOpt = userRepository.findById(event.getInteraction().getUser().getId().asLong());

        if (userEntityOpt.isEmpty()) {
            return event.reply("You never read this message.").withEphemeral(true);
        }

        if (userEntityOpt.get().getPoints() == 0) {
            return event.reply("You have no points.").withEphemeral(true);
        }

        List<Id> ids = event.getInteraction().getMember().get().getMemberData().roles();

        if (!ids.contains(Id.of(HelperRole))) {
            if (userEntityOpt.get().getPoints() >= 10) {
                return event.reply("Hey <#1054472540485066752L>. <@%s> can promote to Helper! *It may take us a few days to discuss it.*");
            } else {
                return event.reply("You need 10 points to promote.");
            }
        }

        if (!ids.contains(Id.of(CollaboratorRole))) {
            if (userEntityOpt.get().getPoints() >= 25) {
                return event.reply("Hey <#1054472540485066752L>. <@%s> can promote to Collaborator! *It may take us a few days to discuss it.*");
            } else {
                return event.reply("You need 25 points to promote.");
            }
        }

        if (!ids.contains(Id.of(DiscordStaffRole))) {
            if (userEntityOpt.get().getPoints() >= 50) {
                return event.reply("Hey <#1054472540485066752L>. <@%s> can promote to Discord Staff! *It may take us a few days to discuss it.*");
            } else {
                return event.reply("You need 50 points to promote.");
            }
        }

        if (!ids.contains(Id.of(SteelCodeRole))) {
            if (userEntityOpt.get().getPoints() >= 100) {
                return event.reply("Hey <#1054472540485066752L>. <@%s> can promote to SteelCodeTeam! *It may take us a few days to discuss it.*");
            } else {
                return event.reply("You need 100 points to promote.  \n*(To access this role, not only your points matter, but also the trust we have in you, and what you can contribute directly to the team)*");
            }
        }

        return event.reply("You cannot promote!").withEphemeral(true);
    }
}
